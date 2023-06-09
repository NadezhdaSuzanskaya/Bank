package dao.mysql;

import dao.interfaces.IDaoSalary;
import model.person.BankEmployee;
import model.person.Salary;
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

public class SalaryDao implements IDaoSalary {
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
    public void create(Salary salary) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into salary values (?,?,?,?) ");
            statement.setInt(1, salary.getIdSalary());
            statement.setInt(2, salary.getBankEmployee().getIdEmployee());
            statement.setDouble(3, salary.getSalarySum());
            statement.setDouble(4, salary.getBonus());
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'insert into salary' query", e);
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from salary where id_salary=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'delete from salary' query", e);
        }
    }

    @Override
    public List<Salary> getAllElements() throws SQLException {
        List<Salary> salaries = new ArrayList<>();
        BankEmployeeDao employeeDao = new BankEmployeeDao();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM salary");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Salary salary = new Salary();
                salary.setIdSalary(result.getInt("id_salary"));
                salary.setSalarySum(result.getDouble("salary_sum"));
                salary.setBonus(result.getDouble("bonus"));
                BankEmployee bankEmpl = employeeDao.getById((result.getInt("id_employee")));
                if (bankEmpl != null) {
                    salary.setBankEmployee(bankEmpl);
                }


                salaries.add(salary);
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM salary' query", e);
        }
        return salaries;
    }

    @Override
    public Salary getById(int id) throws SQLException {
        Salary salary = new Salary();
        BankEmployeeDao employeeDao = new BankEmployeeDao();
        JobTitleDao jobDao = new JobTitleDao();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM salary where id_salary =?");
            LOGGER.info(statement);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                salary.setIdSalary(result.getInt("id_salary"));
                salary.setSalarySum(result.getDouble("salary_sum"));
                salary.setBonus(result.getDouble("bonus"));
                BankEmployee bankEmpl = employeeDao.getById((result.getInt("id_employee")));
                if (bankEmpl != null) {
                    salary.setBankEmployee(bankEmpl);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM salary' query", e);
        }
        return salary;
    }

    @Override
    public void updateSalaryByID(int id, double salary) {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("update salary set salary_sum =? where id_salary =?");
            statement.setDouble(1, salary);
            statement.setInt(2, id);
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'update salary by id' query", e);
        }
    }
}
