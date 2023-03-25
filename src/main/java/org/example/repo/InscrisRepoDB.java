package org.example.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Cursa;
import org.example.domain.Inscris;
import org.example.domain.Participant;
import org.example.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

public class InscrisRepoDB implements IInscrisRepo {
    private final JDBCUtils jdbcUtils;
    private final ICursaRepo raceRepository;
    private final IParticipantRepo participantRepository;
    private static final Logger logger = LogManager.getLogger();

    public InscrisRepoDB(
            final Properties properties, final ICursaRepo raceRepository,
            final IParticipantRepo participantRepository) {
        logger.info("Initializing InscrisRepoDB with properties: {} ", properties);
        jdbcUtils = new JDBCUtils(properties);
        this.raceRepository = raceRepository;
        this.participantRepository = participantRepository;
    }

    @Override
    public Collection<Inscris> getInscrisiDupaCursa(Long raceID) {
        logger.traceEntry("getInscrisiDupaCursa with task of id={} ", raceID);
        Connection connection = jdbcUtils.getConnection();
        Collection<Inscris> raceEntries = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Inscris WHERE idCursa=?")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Inscris raceEntry = extract(resultSet);
                    raceEntries.add(raceEntry);
                }
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
        logger.traceExit(raceEntries);
        return raceEntries;
    }

    @Override
    public Optional<Inscris> get(Long id) {
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from Inscris where idInscris=?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Inscris raceEntry = extract(resultSet);
                    logger.traceExit(raceEntry);
                    return Optional.of(raceEntry);
                }
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
        logger.traceExit("null");
        return Optional.empty();
    }

    @Override
    public Collection<Inscris> read() {
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        Collection<Inscris> raceEntries = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Inscris")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Inscris raceEntry = extract(resultSet);
                    raceEntries.add(raceEntry);
                }
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
        logger.traceExit(raceEntries);
        return raceEntries;
    }

    @Override
    public void add(Inscris newEntity) {
        logger.traceEntry("updating with task {} ", newEntity);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Inscris (idParticipant, idCursa) values (?,?)")) {
            preparedStatement.setLong(1, newEntity.getParticipant().getId());
            preparedStatement.setLong(2, newEntity.getCursa().getId());
            int result = preparedStatement.executeUpdate();
            logger.traceExit("Updated {} instances", result);
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
    }

    @Override
    public void delete(Long id) {
        logger.traceEntry("deleting task of id={} ", id);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Inscris where idInsris=?")) {
            preparedStatement.setLong(1, id);
            int result = preparedStatement.executeUpdate();
            logger.traceExit("Deleted {} instances", result);
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
    }

    @Override
    public void update(Inscris updatedEntity) {
    }

    private Inscris extract(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long participantID = resultSet.getLong("participantID");
        Long raceID = resultSet.getLong("raceID");

        final Optional<Cursa> race = raceRepository.get(raceID);
        final Optional<Participant> participant = participantRepository.get(participantID);
        if (participant.isPresent() && race.isPresent()) {
            Inscris raceEntry = new Inscris(id, participant.get(), race.get());

            return raceEntry;
        } else {
            throw new SQLException("Database error: Unable to reference participant and/or race for Race Entry");
        }
    }
}