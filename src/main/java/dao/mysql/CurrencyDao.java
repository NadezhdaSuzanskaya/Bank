package dao.mysql;

import dao.interfaces.IDaoCurrency;
import model.account.Currency;
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

public class CurrencyDao implements IDaoCurrency {
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
    public void create(Currency currency) throws SQLException {
        loadProperties();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into currency values (?,?,?) ");
            statement.setInt(1, currency.getIdCurrency());
            statement.setString(2, currency.getCurrencyName());
            statement.setString(3, currency.getCurrencyCode());
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("insert into currency", e);
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from currency where id_currency=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'delete from currency' query", e);
        }
    }

    @Override
    public List<Currency> getAllElements() throws SQLException {
        List<Currency> currencies = new ArrayList<>();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM currency");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Currency currency = new Currency();
                currency.setIdCurrency(result.getInt("id_currency"));
                currency.setCurrencyName(result.getString("currency_name"));
                currency.setCurrencyCode(result.getString("currency_code"));
                currencies.add(currency);
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM currency' query", e);
        }
        return currencies;
    }

    @Override
    public Currency getById(int id) throws SQLException {
        Currency currency = new Currency();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM currency where id_currency=?");
            statement.setInt(1, id);
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                currency.setIdCurrency(result.getInt("id_currency"));
                currency.setCurrencyName(result.getString("currency_name"));
                currency.setCurrencyCode(result.getString("currency_code"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM currency bu id' query", e);
        }
        return currency;
    }

    @Override
    public Currency getCurrencyByCode(String code) {
        Currency currency = new Currency();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM currency where currency_code=?");
            statement.setString(1, code);
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                currency.setIdCurrency(result.getInt("id_currency"));
                currency.setCurrencyName(result.getString("currency_name"));
                currency.setCurrencyCode(result.getString("currency_code"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM currency by code' query", e);
        }
        return currency;
    }
}
