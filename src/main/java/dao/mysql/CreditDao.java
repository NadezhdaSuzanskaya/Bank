package dao.mysql;

import dao.interfaces.IDaoCredit;
import model.person.Client;
import model.products.Credit;
import model.products.CreditType;
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
import java.sql.Date;

public class CreditDao implements IDaoCredit {
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
    public void create(Credit credit) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into credit values (?,?,?,?,?,?)");
            statement.setInt(1, credit.getIdCredit());
            statement.setDouble(2, credit.getInitial_sum());
            statement.setDate(3, credit.getStart_date());
            statement.setDate(4, credit.getEnd_date());
            statement.setInt(5, credit.getCreditType().getIdCreditType());
            statement.setInt(6, credit.getClient().getIdClient());
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'insert into credit' query", e);
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from credit where id_credit=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'delete from credit' query", e);
        }
    }

    @Override
    public List<Credit> getAllElements() throws SQLException {
        List<Credit> credits = new ArrayList<>();
        ClientDao clientDao = new ClientDao();
        CreditTypeDao credTypeDao = new CreditTypeDao();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM credit");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Credit credit = new Credit();
                credit.setIdCredit(result.getInt("id_credit"));
                credit.setInitial_sum(result.getDouble("sum"));
                credit.setStart_date(result.getDate("start_date"));
                credit.setEnd_date(result.getDate("end_date"));

                CreditType credType = credTypeDao.getById((result.getInt("id_credit_type")));
                if (credType != null) {
                    credit.setCreditType(credType);
                }
                Client client = clientDao.getById((result.getInt("id_client")));
                if (client != null) {
                    credit.setClient(client);
                }

                credits.add(credit);
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM credit' query", e);
        }
        return credits;
    }

    @Override
    public Credit getById(int id) throws SQLException {
        ClientDao clientDao = new ClientDao();
        CreditTypeDao credTypeDao = new CreditTypeDao();
        Credit credit = new Credit();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM credit where id_credit=?");
            statement.setInt(1, id);
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                credit.setIdCredit(result.getInt("id_credit"));
                credit.setInitial_sum(result.getDouble("sum"));
                credit.setStart_date(result.getDate("start_date"));
                credit.setEnd_date(result.getDate("end_date"));

                CreditType credType = credTypeDao.getById((result.getInt("id_credit_type")));
                if (credType != null) {
                    credit.setCreditType(credType);
                }
                Client client = clientDao.getById((result.getInt("id_client")));
                if (client != null) {
                    credit.setClient(client);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM credit' query", e);
        }
        return credit;
    }

    @Override
    public void updateCreditDatesByClientID(int id, Date beginDate, Date endDate) {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("update credit set start_date =?, end_date=? where id_credit =?");
            statement.setDate(1, beginDate);
            statement.setDate(2, endDate);
            statement.setInt(3, id);
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'update credit by id' query", e);
        }
    }
}
