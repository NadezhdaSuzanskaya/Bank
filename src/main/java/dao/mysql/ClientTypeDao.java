package dao.mysql;

import dao.interfaces.IDaoClientType;
import model.enums.ClientTypeName;
import model.person.ClientType;
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

public class ClientTypeDao implements IDaoClientType {
    Logger LOGGER = LogManager.getLogger();
    Properties properties = new Properties();
    ClientType clientType = new ClientType();

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
    public void create(ClientType clientType) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into client_type values (?,?) ");
            statement.setInt(1, clientType.getIdClientType());
            statement.setString(2, clientType.getClientType().getClientType());
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("insert into client_type", e);
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from client_type where id_client_type=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'delete from client_type' query", e);
        }
    }

    @Override
    public List<ClientType> getAllElements() throws SQLException {
        List<ClientType> typeList = new ArrayList<>();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM client_type");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ClientType clientType = new ClientType();
                clientType.setIdClientType(result.getInt("id_client_type"));
                String clientTypeString = result.getString("client_type").trim();
                for (ClientTypeName enumValue : ClientTypeName.values()) {
                    if (enumValue.getClientType().equalsIgnoreCase(clientTypeString)) {
                        clientType.setClientType(enumValue);
                        break;
                    }
                }
                typeList.add(clientType);
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM client_type' query", e);
        }
        return typeList;
    }

    @Override
    public ClientType getById(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("select * from client_type where id_client_type = ?");
            statement.setInt(1, id);
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                clientType.setIdClientType(result.getInt("id_client_type"));
                String clientTypeString = result.getString("client_type").trim();
                for (ClientTypeName enumValue : ClientTypeName.values()) {
                    if (enumValue.getClientType().equalsIgnoreCase(clientTypeString)) {
                        clientType.setClientType(enumValue);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL query", e);
        }
        return clientType;
    }

    @Override
    public ClientType getClientTypeByClientType(String type) {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("select * from client_type where client_type = ?");
            statement.setString(1, "%" + type + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                clientType.setIdClientType(result.getInt("id_client_type"));
                String jobNameString = result.getString("client_type ").trim();
                for (ClientTypeName enumValue : ClientTypeName.values()) {
                    if (enumValue.getClientType().equalsIgnoreCase(jobNameString)) {
                        clientType.setClientType(enumValue);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'select * from client_type by client_type ' query", e);
        }
        return clientType;
    }
}
