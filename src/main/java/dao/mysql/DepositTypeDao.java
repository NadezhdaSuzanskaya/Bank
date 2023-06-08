package dao.mysql;

import dao.interfaces.IDaoDepositType;
import model.enums.ClientTypeName;
import model.enums.ProductTypeName;
import model.products.CreditType;
import model.products.DepositType;
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

public class DepositTypeDao implements IDaoDepositType {

    Logger LOGGER = LogManager.getLogger();
    Properties properties = new Properties();
    DepositType depositType = new DepositType();

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
    public void create(DepositType depositType) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into deposit_type values (?,?,?,?,?,?) ");
            statement.setInt(1, depositType.getIdDeposityType());
            statement.setString(2, depositType.getTypeName().getProduct());
            statement.setDouble(3, depositType.getPersent());
            statement.setInt(4, depositType.getTerm());
            statement.setBoolean(5, depositType.isReplenishment());
            statement.setBoolean(6, depositType.isEarlywithdrawal());
            LOGGER.info(statement);
            statement.execute();
        }
        catch (SQLException e) {
            LOGGER.error("insert into deposit_type", e);
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from deposit_type where id_deposit_type=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        }
        catch (SQLException e) {
            LOGGER.error("Error executing SQL 'delete from deposit_type' query", e);
        }
    }

    @Override
    public List<DepositType> getAllElements() throws SQLException {
        List<DepositType> typeList = new ArrayList<>();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM deposit_type");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DepositType depType = new DepositType();
                depType.setIdDeposityType(result.getInt("id_deposit_type"));
                String depTypeString = result.getString("name").trim();
                for (ProductTypeName enumValue : ProductTypeName.values()) {
                    if (enumValue.getProduct().equalsIgnoreCase(depTypeString)) {
                        depType.setTypeName(enumValue);
                        break;
                    }
                }
                depType.setPersent(result.getDouble("persent"));
                depType.setTerm(result.getInt("term"));
                depType.setReplenishment(result.getBoolean("replenishment"));
                depType.setEarlywithdrawal(result.getBoolean("early_withdrawal"));
                typeList.add(depType);
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM deposit_type' query", e);
        }
        LOGGER.info(typeList);
        return typeList;
    }

    @Override
    public DepositType getById(int id) throws SQLException {
        loadProperties();
        DepositType depType = new DepositType();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM deposit_type where id_deposit_type=?");
            statement.setInt(1, id );
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {

                depType.setIdDeposityType(result.getInt("id_deposit_type"));
                String creditTypeString = result.getString("name").trim();
                for (ProductTypeName enumValue : ProductTypeName.values()) {
                    if (enumValue.getProduct().equalsIgnoreCase(creditTypeString)) {
                        depType.setTypeName(enumValue);
                        break;
                    }
                }
                depType.setPersent(result.getDouble("persent"));
                depType.setTerm(result.getInt("term"));
                depType.setReplenishment(result.getBoolean("replenishment"));
                depType.setEarlywithdrawal(result.getBoolean("early_withdrawal"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM credit_type by ID' query", e);
        }
        return depType;
    }

    @Override
    public DepositType getDepositTypeByName(String name) throws SQLException {
        loadProperties();
        DepositType depType = new DepositType();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("select * from deposit_type where name = ?");
            statement.setString(1, "%" + name + "%");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {

                depType.setIdDeposityType(result.getInt("id_deposit_type"));
                String creditTypeString = result.getString("name").trim();
                for (ProductTypeName enumValue : ProductTypeName.values()) {
                    if (enumValue.getProduct().equalsIgnoreCase(creditTypeString)) {
                        depType.setTypeName(enumValue);
                        break;
                    }
                }
                depType.setPersent(result.getDouble("persent"));
                depType.setTerm(result.getInt("term"));
                depType.setReplenishment(result.getBoolean("replenishment"));
                depType.setEarlywithdrawal(result.getBoolean("early_withdrawal"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'select * from deposit_type by name ' query", e);
        }
        return depType;
    }

}
