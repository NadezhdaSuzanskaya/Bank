package dao.mysql;

import dao.interfaces.IDaoOperations;
import model.account.Account;
import model.account.OperationType;
import model.account.Operations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OperationsDao implements IDaoOperations {
    Logger LOGGER = LogManager.getLogger();
    Properties properties = new Properties();

    private void loadProperties() {
        try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
            properties.load(input);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void create(Operations operations) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into operations values (?,?,?,?,?) ");
            statement.setInt(1, operations.getIdOperation());
            statement.setDouble(2, operations.getOperationSum());
            statement.setDate(3, operations.getOperationDate());
            statement.setInt(4, operations.getOperationType().getIdOperationType());
            statement.setInt(5, operations.getAccount().getIdAccount());
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'insert into operations' query", e);
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from operations where id_operations=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'delete from operations' query", e);
        }
    }

    @Override
    public List<Operations> getAllElements() throws SQLException {
        List<Operations> operations = new ArrayList<>();
        OperationTypeDao operationTypeDao = new OperationTypeDao();
        AccountDao accDao = new AccountDao();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM operations");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Operations operation = new Operations();
                operation.setIdOperation(result.getInt("id_operations"));
                operation.setOperationSum(result.getDouble("sum"));
                operation.setOperationDate(result.getDate("operation_date"));
                OperationType operationType = operationTypeDao.getById((result.getInt("id_operation_type")));
                if (operationType != null) {
                    operation.setOperationType(operationType);
                }
                Account account = accDao.getById((result.getInt("id_account")));
                if (account != null) {
                    operation.setAccount(account);
                }
                operations.add(operation);
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM operations' query", e);
        }
        return operations;
    }

    @Override
    public Operations getById(int id) throws SQLException {
        Operations operation = new Operations();
        OperationTypeDao operationTypeDao = new OperationTypeDao();
        AccountDao accDao = new AccountDao();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM operations");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                operation.setIdOperation(result.getInt("id_operations"));
                operation.setOperationSum(result.getDouble("sum"));
                operation.setOperationDate(result.getDate("operation_date"));
                OperationType operationType = operationTypeDao.getById((result.getInt("id_operation_type")));
                if (operationType != null) {
                    operation.setOperationType(operationType);
                }
                Account account = accDao.getById((result.getInt("id_account")));
                if (account != null) {
                    operation.setAccount(account);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM operations' query", e);
        }
        return operation;
    }

    @Override
    public void updateOperationSumById(int id, double sum) {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("update operations set sum =? where id_operations =?");
            statement.setDouble(1, sum);
            statement.setInt(2, id);
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'update operation's  sum by id' query", e);
        }
    }
}
