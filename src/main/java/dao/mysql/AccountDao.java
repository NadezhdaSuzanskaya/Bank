package dao.mysql;

import dao.interfaces.IDaoAccount;
import model.account.Account;
import model.account.Currency;
import model.person.BankEmployee;
import model.person.Client;
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

public class AccountDao implements IDaoAccount {
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
    public void create(Account account) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into account values (?,?,?,?,?,?) ");
            statement.setInt(1, account.getIdAccount());
            statement.setString(2, account.getAccount());
            statement.setDate(3, account.getStartDate());
            statement.setInt(4, account.getCurrency().getIdCurrency());
            statement.setInt(5, account.getClient().getIdClient());
            statement.setInt(6, account.getBankEmployee().getIdEmployee());
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'insert into account' query", e);
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from account where id_account=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'delete from account' query", e);
        }
    }

    @Override
    public List<Account> getAllElements() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        CurrencyDao currencyDao = new CurrencyDao();
        ClientDao clientDao = new ClientDao();
        BankEmployeeDao employeeDao = new BankEmployeeDao();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM account");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Account account = new Account();
                account.setIdAccount(result.getInt("id_account"));
                account.setAccount(result.getString("account"));
                account.setStartDate(result.getDate("start_date"));
                Currency currency = currencyDao.getById((result.getInt("id_currency")));
                if (currency != null) {
                    account.setCurrency(currency);
                }
                Client client = clientDao.getById((result.getInt("id_client")));
                if (client != null) {
                    account.setClient(client);
                }
                BankEmployee employee = employeeDao.getById((result.getInt("id_employee")));
                if (employee != null) {
                    account.setBankEmployee(employee);
                }
                accounts.add(account);
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM account' query", e);
        }
        return accounts;
    }

    @Override
    public Account getById(int id) throws SQLException {
        Account account = new Account();
        CurrencyDao currencyDao = new CurrencyDao();
        ClientDao clientDao = new ClientDao();
        BankEmployeeDao employeeDao = new BankEmployeeDao();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM account where id_account=?");
            statement.setInt(1, id);
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                account.setIdAccount(result.getInt("id_account"));
                account.setAccount(result.getString("account"));
                account.setStartDate(result.getDate("start_date"));
                Currency currency = currencyDao.getById((result.getInt("id_currency")));
                if (currency != null) {
                    account.setCurrency(currency);
                }
                Client client = clientDao.getById((result.getInt("id_client")));
                if (client != null) {
                    account.setClient(client);
                }
                BankEmployee employee = employeeDao.getById((result.getInt("id_employee")));
                if (employee != null) {
                    account.setBankEmployee(employee);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM account' query", e);
        }
        return account;
    }
}
