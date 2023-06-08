package dao.mysql;

import dao.interfaces.IDaoCreditType;
import model.enums.ClientTypeName;
import model.enums.ProductTypeName;
import model.person.ClientType;
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

public class CreditTypeDao implements IDaoCreditType {
    Logger LOGGER = LogManager.getLogger();
    Properties properties = new Properties();
    CreditType creditType = new CreditType();

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
    public void create(CreditType creditType) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into credit_type values (?,?,?,?,?) ");
            statement.setInt(1, creditType.getIdCreditType());
            statement.setString(2, creditType.getTypeName().getProduct());
            statement.setDouble(3, creditType.getPersent());
            statement.setInt(4, creditType.getTerm());
            statement.setBoolean(5, creditType.isEralyRepayment());

            LOGGER.info(statement);
            statement.execute();
        }
        catch (SQLException e) {
            LOGGER.error("insert into credit_type", e);
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from credit_type where id_credit_type=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        }
        catch (SQLException e) {
            LOGGER.error("Error executing SQL 'delete from credit_type' query", e);
        }
    }

    @Override
    public List<CreditType> getAllElements() throws SQLException {
        List<CreditType> typeList = new ArrayList<>(); // Create a list  for departments
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM credit_type");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                CreditType creditType = new CreditType();
                creditType.setIdCreditType(result.getInt("id_credit_type"));
                String creditTypeString = result.getString("name").trim();
                for (ProductTypeName enumValue : ProductTypeName.values()) {
                    if (enumValue.getProduct().equalsIgnoreCase(creditTypeString)) {
                        creditType.setTypeName(enumValue);
                        break;
                    }
                }
                creditType.setPersent(result.getDouble("persent"));
                creditType.setTerm(result.getInt("term"));
                creditType.setEralyRepayment(result.getBoolean("early_repayment"));
                typeList.add(creditType);
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM credit_type' query", e);
        }
        LOGGER.info(typeList);
        return typeList;
    }

    @Override
    public CreditType getById(int id) throws SQLException {
        loadProperties();
        CreditType creditType = new CreditType();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM credit_type where id_credit_type=?");
            statement.setInt(1, id );
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                creditType.setIdCreditType(result.getInt("id_credit_type"));
                String creditTypeString = result.getString("name").trim();
                for (ProductTypeName enumValue : ProductTypeName.values()) {
                    if (enumValue.getProduct().equalsIgnoreCase(creditTypeString)) {
                        creditType.setTypeName(enumValue);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM credit_type by ID' query", e);
        }
        return creditType;
    }

    @Override
    public void updateTermAndPercentByName(String name, int term, int percent) {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("update credit_type set term =? , persent =? where name =?");
            statement.setInt(1, term);
            statement.setDouble(2, percent);
            statement.setString(3, name);
            LOGGER.info(statement);
            statement.execute();
        }
        catch (SQLException e) {
            LOGGER.error("Error executing SQL 'update client surname by passport' query", e);
        }
    }
}
