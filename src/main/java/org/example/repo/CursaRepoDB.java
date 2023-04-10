package org.example.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Cursa;
import org.example.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

public class CursaRepoDB implements ICursaRepo {
    private final JDBCUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();

    public CursaRepoDB(final Properties properties) {
        logger.info("Initializing CursaRepoDB with properties: {} ", properties);
        jdbcUtils = new JDBCUtils(properties);
    }

    @Override
    public Collection<Cursa> getCursaDupaMotor(Integer engineCapacity) {
        logger.traceEntry("getRacesByEngineCapacity with task {} ", engineCapacity);
        Connection connection = jdbcUtils.getConnection();
        Collection<Cursa> races = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM races WHERE engineCc=?")) {
            preparedStatement.setInt(1, engineCapacity);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Cursa race = extract(resultSet);
                    races.add(race);
                }
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
        logger.traceExit(races);
        return races;
    }


    @Override
    public Optional<Cursa> getCursaDupaNume(String name) {
        logger.traceEntry("getCursaDupaNume with task {} ", name);
        Connection connection = jdbcUtils.getConnection();
        Collection<Cursa> races = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Cursa WHERE numeCursa=?")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Optional<Cursa> race = Optional.of(extract(resultSet));
                    logger.traceExit(race.get());
                    return race;
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
    public Optional<Cursa> get(Long id) {
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Cursa WHERE idCursa=?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Cursa race = extract(resultSet);
                    logger.traceExit(race);
                    return Optional.of(race);
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
    public Collection<Cursa> read() {
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        Collection<Cursa> races = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Cursa")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Cursa race = extract(resultSet);
                    races.add(race);
                }
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
        logger.traceExit(races);
        return races;
    }

    @Override
    public void add(Cursa newEntity) {
        logger.traceEntry("saving task {} ", newEntity);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Cursa (numeCursa, nrParticipanti, capacitateCilindrica) VALUES (?,?,?)")) {
            preparedStatement.setString(1, newEntity.getNumeCursa());
            preparedStatement.setInt(2, newEntity.getNrParticipanti());
            preparedStatement.setInt(3, newEntity.getCapacitateCilindrica());

            final int result = preparedStatement.executeUpdate();
            logger.traceExit("Saved {} instances ", result);
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
                "DELETE FROM Cursa WHERE idCursa=?")) {
            preparedStatement.setLong(1, id);
            final int result = preparedStatement.executeUpdate();
            logger.traceExit("Deleted {} instances", result);
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
    }

    @Override
    public void update(Cursa updatedEntity) {
        logger.traceEntry("updating with task {} ", updatedEntity);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Cursa SET numeCursa=?, nrParticipanti=?, capacitateCilindrica=? where idCursa=?")) {
            preparedStatement.setString(1, updatedEntity.getNumeCursa());
            preparedStatement.setInt(2, updatedEntity.getNrParticipanti());
            preparedStatement.setInt(3, updatedEntity.getCapacitateCilindrica());
            preparedStatement.setLong(4, updatedEntity.getId());
            int result = preparedStatement.executeUpdate();
            logger.traceExit("Updated {} instances");
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
    }

    private Cursa extract(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("idCursa");
        String name = resultSet.getString("numeCursa");
        Integer number = resultSet.getInt("nrParticipanti");
        Integer engineCapacity = resultSet.getInt("capacitateCilindrica");

        Cursa race = new Cursa(id, name, 0, engineCapacity);

        return race;
    }
}