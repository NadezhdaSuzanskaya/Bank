package dao.mysql;

import dao.interfaces.IDaoDeposit;
import model.person.Client;
import model.products.Deposit;
import model.products.DepositType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DepositDao implements IDaoDeposit {
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
    public void create(Deposit deposit) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into deposit values (?,?,?,?,?,?)");
            statement.setInt(1, deposit.getIdDeposit());
            statement.setDouble(2, deposit.getInitial_sum());
            statement.setDate(3, deposit.getStart_date());
            statement.setDate(4, deposit.getEnd_date());
            statement.setInt(5, deposit.getDepositType().getIdDeposityType());
            statement.setInt(6, deposit.getClient().getIdClient());
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'insert into deposit' query", e);
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from deposit where id_deposit=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'delete from deposit' query", e);
        }
    }

    @Override
    public List<Deposit> getAllElements() throws SQLException {
        List<Deposit> deposits = new ArrayList<>();
        ClientDao clientDao = new ClientDao();
        DepositTypeDao depTypeDao = new DepositTypeDao();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM deposit");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Deposit deposit = new Deposit();
                deposit.setIdDeposit(result.getInt("id_deposit"));
                deposit.setInitial_sum(result.getDouble("initial_amount"));
                deposit.setStart_date(result.getDate("start_date"));
                deposit.setEnd_date(result.getDate("end_date"));

                DepositType depType = depTypeDao.getById((result.getInt("id_deposit_type")));
                if (depType != null) {
                    deposit.setDepositType(depType);
                    ;
                }
                Client client = clientDao.getById((result.getInt("id_client")));
                if (client != null) {
                    deposit.setClient(client);
                }

                deposits.add(deposit);
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM deposit' query", e);
        }
        return deposits;
    }

    @Override
    public Deposit getById(int id) throws SQLException {
        ClientDao clientDao = new ClientDao();
        DepositTypeDao depTypeDao = new DepositTypeDao();
        Deposit dep = new Deposit();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM deposit where id_deposit=?");
            statement.setInt(1, id);
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                dep.setIdDeposit(result.getInt("id_deposit"));
                dep.setInitial_sum(result.getDouble("initial_amount"));
                dep.setStart_date(result.getDate("start_date"));
                dep.setEnd_date(result.getDate("end_date"));
                DepositType depType = depTypeDao.getById((result.getInt("id_deposit_type")));
                if (depType != null) {
                    dep.setDepositType(depType);
                }
                Client client = clientDao.getById((result.getInt("id_client")));
                if (client != null) {
                    dep.setClient(client);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM deposit' query", e);
        }
        return dep;
    }

    @Override
    public void updateDatesByClientID(int id, Date beginDate, Date endDate) {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("update deposit set start_date =?, end_date=? where id_deposit =?");
            statement.setDate(1, beginDate);
            statement.setDate(2, endDate);
            statement.setInt(3, 2);
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'update deposit by id' query", e);
        }
    }
}
