package org.example.repo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Participant;
import org.example.utils.JDBCUtils;

import java.sql.*;
import java.util.*;


public class ParticipantRepoDB implements IParticipantRepo {
    private final JDBCUtils dbUtils;
    private static final Logger logger = LogManager.getLogger();

    public ParticipantRepoDB(final Properties properties) {
        logger.info("Initializing ParticipantRepoDB with properties: {} ", properties);
        dbUtils = new JDBCUtils(properties);
    }

    @Override
    public Collection<Participant> getParticipantiDupaEchipa(Long teamID) {
        logger.traceEntry();
        Connection connection = dbUtils.getConnection();
        Collection<Participant> participants = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Participant WHERE idEchipa=?")) {
            preparedStatement.setLong(1, teamID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Participant participant = extract(resultSet);
                    participants.add(participant);
                }
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
        logger.traceExit(participants);
        return participants;
    }

    @Override
    public Optional<Participant> get(Long id) {
        logger.traceEntry();
        Connection connection = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM participants WHERE id=?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Participant participant = extract(resultSet);
                    logger.traceExit(participant);
                    return Optional.of(participant);
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
    public Collection<Participant> read() {
        logger.traceEntry();
        Connection connection = dbUtils.getConnection();
        Collection<Participant> participants = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Participant")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Participant participant = extract(resultSet);
                    participants.add(participant);
                }
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
        logger.traceExit(participants);
        return participants;
    }

    @Override
    public void add(Participant newEntity) {
        logger.traceEntry("saving task {} ", newEntity);
        Connection connection = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Participant (nume, prenume, capacitateMotor, idEchipa) " +
                        "VALUES (?,?,?,?)")) {
            setParams(newEntity, preparedStatement);
            final int result = preparedStatement.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
    }

    @Override
    public void delete(Long id) {
        logger.traceEntry("deleting task of id={} ", id);
        Connection connection = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Participant WHERE idParticipant=?")) {
            preparedStatement.setLong(1, id);
            final int result = preparedStatement.executeUpdate();
            logger.trace("Deleted {} instances", result);
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
    }

    @Override
    public void update(Participant updatedEntity) {
        logger.traceEntry("updating with task {} ", updatedEntity);
        Connection connection = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Participant SET nume=?, prenume=?, capacitateMotor=?, idEchipa=? WHERE idParticipant=?")) {
            setParams(updatedEntity, preparedStatement);
            preparedStatement.setLong(5, updatedEntity.getId());
            final int result = preparedStatement.executeUpdate();
            logger.trace("Updated {} instances", result);
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
    }

    private void setParams(Participant updatedEntity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, updatedEntity.getNume());
        preparedStatement.setString(2, updatedEntity.getPrenume());
        preparedStatement.setInt(3, updatedEntity.getCapacitateMotor());
        if (updatedEntity.getEchipa() != null) {
            preparedStatement.setLong(4, updatedEntity.getEchipa());
        } else {
            preparedStatement.setNull(4, Types.INTEGER);
        }
    }

    private Participant extract(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("idParticipant");
        String firstName = resultSet.getString("nume");
        String lastName = resultSet.getString("prenume");
        Integer engineCapacity = resultSet.getInt("capacitateMotor");
        Long teamID = resultSet.getLong("idEchipa");

        Participant participant = new Participant(id, firstName, lastName, engineCapacity, teamID);
        return participant;
    }
}



