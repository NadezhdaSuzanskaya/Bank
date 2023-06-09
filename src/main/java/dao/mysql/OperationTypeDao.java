package dao.mysql;

import dao.interfaces.IDaoOperationType;
import model.account.OperationType;
import model.enums.OperationsTypeName;
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

public class OperationTypeDao implements IDaoOperationType {
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
    public void create(OperationType operationType) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into operation_type values (?,?)");
            statement.setInt(1, operationType.getIdOperationType());
            statement.setString(2, operationType.getOperationTypeName().getOperationsTypeName());
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("insert into operation_type", e);
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from operation_type where id_operation_type=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'delete from deposit_type' query", e);
        }
    }

    @Override
    public List<OperationType> getAllElements() throws SQLException {
        List<OperationType> typeList = new ArrayList<>();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM operation_type");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                OperationType operationType = new OperationType();
                operationType.setIdOperationType(result.getInt("id_operation_type"));
                String operationTypeString = result.getString("name").trim();
                for (OperationsTypeName enumValue : OperationsTypeName.values()) {
                    if (enumValue.getOperationsTypeName().equalsIgnoreCase(operationTypeString)) {
                        operationType.setOperationTypeName(enumValue);
                        break;
                    }
                }
                typeList.add(operationType);
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM operation_type' query", e);
        }
        return typeList;
    }

    @Override
    public OperationType getById(int id) throws SQLException {
        loadProperties();
        OperationType operationType = new OperationType();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM operation_type where id_operation_type =?");
            statement.setInt(1, id);
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {

                operationType.setIdOperationType(result.getInt("id_operation_type"));
                String operationTypeString = result.getString("name").trim();
                for (OperationsTypeName enumValue : OperationsTypeName.values()) {
                    if (enumValue.getOperationsTypeName().equalsIgnoreCase(operationTypeString)) {
                        operationType.setOperationTypeName(enumValue);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM operation_type by id' query", e);
        }
        return operationType;
    }

    @Override
    public OperationType getOperationTypeByName(String name) {
        loadProperties();
        OperationType operationType = new OperationType();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM operation_type where name =?");
            statement.setString(1, "%" + name + "%");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {

                operationType.setIdOperationType(result.getInt("id_operation_type"));
                String operationTypeString = result.getString("name").trim();
                for (OperationsTypeName enumValue : OperationsTypeName.values()) {
                    if (enumValue.getOperationsTypeName().equalsIgnoreCase(operationTypeString)) {
                        operationType.setOperationTypeName(enumValue);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM operation_type by name' query", e);
        }
        return operationType;
    }
}
