package org.example.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Echipa;
import org.example.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

public class EchipaRepoDB implements IEchipaRepo {
    private final JDBCUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();

    public EchipaRepoDB(Properties properties) {
        logger.info("Initializing EchipaRepoDB with properties: {} ", properties);
        jdbcUtils = new JDBCUtils(properties);
    }

    @Override
    public Long getEchipaDupaNume(String teamName) {
        logger.traceEntry("getEchipaDupaNume with task {} ", teamName);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Echipa WHERE numeEchipa=?")) {
            preparedStatement.setString(1, teamName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Echipa team = extract(resultSet);
                    logger.traceExit(team);
                    return team.getId();
                }
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
        logger.traceExit("null");
        return 0l;
    }

    @Override
    public Optional<Echipa> get(Long id) {
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Echipa WHERE idEchipa=?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Echipa team = extract(resultSet);
                    logger.traceExit(team);
                    return Optional.of(team);
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
    public Collection<Echipa> read() {
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        Collection<Echipa> teams = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Echipa")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Echipa team = extract(resultSet);
                    teams.add(team);
                }
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
        logger.traceExit(teams);
        return teams;
    }

    @Override
    public void add(Echipa newEntity) {
        logger.traceEntry("saving task {} ", newEntity);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Echipa (numeEchipa) VALUES (?)")) {
            preparedStatement.setString(1, newEntity.getNumeEchipa());
            int result = preparedStatement.executeUpdate();
            logger.traceExit("Saved {} instances");
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
                "DELETE FROM Echipa WHERE idEchipa=?")) {
            preparedStatement.setLong(1, id);
            int result = preparedStatement.executeUpdate();
            logger.traceExit("Deleted {} instances", result);
        } catch (SQLException sqlException) {
            logger.error(sqlException);
            System.err.println("DB Error : " + sqlException);
        }
    }

    @Override
    public void update(Echipa updatedEntity) {
    }

    private Echipa extract(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("idEchipa");
        String name = resultSet.getString("numeEchipa");

        Echipa team = new Echipa(id, name);
        return team;
    }
}
