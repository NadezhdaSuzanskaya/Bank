package dao.mysql;

import dao.interfaces.IDaoClient;
import model.person.*;
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

public class ClientDao implements IDaoClient {

    Logger LOGGER = LogManager.getLogger();
    Properties properties = new Properties();
    Client client = new Client();

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
    public void create(Client client) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into client values (?,?,?,?,?,?,?) ");
            statement.setInt(1, client.getIdClient());
            statement.setString(2, client.getPersonSurname());
            statement.setString(3, client.getPersonName());
            statement.setString(4, client.getPassport());
            statement.setString(5, client.getPhone());
            statement.setString(6, client.getAddress());
            statement.setInt(7, client.getClientType().getIdClientType());
            LOGGER.info(statement);
            statement.execute();
        }
        catch (SQLException e) {
            LOGGER.error("Error executing SQL 'insert into client' query", e);
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from client where id_client=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        }
        catch (SQLException e) {
            LOGGER.error("Error executing SQL 'delete from client' query", e);
        }
    }

    @Override
    public List<Client> getAllElements() throws SQLException {
        List<Client> clients = new ArrayList<>(); // Create a list  for departments
        ClientTypeDao clientDao = new ClientTypeDao();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM client");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Client client = new Client();
                client.setIdClient(result.getInt("id_client"));
                client.setPersonSurname(result.getString("surname"));
                client.setPersonName(result.getString("name"));
                client.setPassport(result.getString("passport"));
                client.setPhone(result.getString("phone"));
                client.setAddress(result.getString("address"));
                ClientType clientType = clientDao.getById((result.getInt("id_client_type")));
                if (clientType != null) {
                    client.setClientType(clientType);
                }


                clients.add(client);
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM client' query", e);
        }
        LOGGER.info(clients);
        return clients;
    }

    @Override
    public Client getById(int id) throws SQLException {
        ClientTypeDao clientDao = new ClientTypeDao();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM client where id_client = ?");
            LOGGER.info(statement);
            statement.setInt(1, id );
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Client client = new Client();
                client.setIdClient(result.getInt("id_client"));
                client.setPersonSurname(result.getString("surname"));
                client.setPersonName(result.getString("name"));
                client.setPassport(result.getString("passport"));
                client.setPhone(result.getString("phone"));
                client.setAddress(result.getString("address"));
                ClientType clientType = clientDao.getById((result.getInt("id_client_type")));
                if (clientType != null) {
                    client.setClientType(clientType);
                }

            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM client by ID' query", e);
        }
        LOGGER.info(client);
        return client;
    }

    @Override
    public void updateClientSurnameByPassport(String surname, String passport) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("update client set surname =? where passport =?");
            statement.setString(1, surname);
            statement.setString(2, passport);
            LOGGER.info(statement);
            statement.execute();
        }
        catch (SQLException e) {
            LOGGER.error("Error executing SQL 'update client surname by passport' query", e);
        }
    }
}
