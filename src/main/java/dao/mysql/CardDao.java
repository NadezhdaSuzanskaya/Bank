package dao.mysql;

import dao.interfaces.IDaoCard;
import model.account.Account;
import model.account.Card;
import model.enums.CardName;
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

public class CardDao implements IDaoCard {
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
    public void create(Card card) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into card values (?,?,?,?,?,?)");
            statement.setInt(1, card.getIdCard());
            statement.setString(2, card.getCardName().getCardName());
            statement.setDate(3, card.getEndDate());
            statement.setBoolean(4, card.isOnlineTransactions());
            statement.setDouble(5, card.isDayLimits());
            statement.setInt(6, card.getAccount().getIdAccount());
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'insert into card' query", e);
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from card where id_card=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'delete from card' query", e);
        }
    }

    @Override
    public List<Card> getAllElements() throws SQLException {
        List<Card> cards = new ArrayList<>();
        AccountDao accDao = new AccountDao();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM card");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Card card = new Card();
                card.setIdCard(result.getInt("id_card"));
                String cardNameString = result.getString("name").trim();
                for (CardName enumValue : CardName.values()) {
                    if (enumValue.getCardName().equalsIgnoreCase(cardNameString)) {
                        card.setCardName(enumValue);
                        break;
                    }
                }
                card.setEndDate(result.getDate("end_date"));
                card.setOnlineTransactions(result.getBoolean("online_transactions"));
                card.setDayLimits(result.getDouble("day_limits"));
                Account acc = accDao.getById((result.getInt("id_account")));
                if (acc != null) {
                    card.setAccount(acc);
                }
                cards.add(card);
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM card' query", e);
        }
        return cards;
    }

    @Override
    public Card getById(int id) throws SQLException {
        AccountDao accDao = new AccountDao();
        Card card = new Card();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM card where id_card=?");
            statement.setInt(1, id);
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                card.setIdCard(result.getInt("id_card"));
                String cardNameString = result.getString("name").trim();
                for (CardName enumValue : CardName.values()) {
                    if (enumValue.getCardName().equalsIgnoreCase(cardNameString)) {
                        card.setCardName(enumValue);
                        break;
                    }
                }
                card.setEndDate(result.getDate("end_date"));
                card.setOnlineTransactions(result.getBoolean("online_transactions"));
                card.setDayLimits(result.getDouble("day_limits"));
                Account acc = accDao.getById((result.getInt("id_account")));
                if (acc != null) {
                    card.setAccount(acc);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM card by id' query", e);
        }
        return card;
    }

    @Override
    public void updateDayLimitsById(int id, double dayLimits) {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("update card set day_limits =? where id_card =?");
            statement.setDouble(1, dayLimits);
            statement.setInt(2, id);
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'update card's  day limits by id' query", e);
        }
    }
}
