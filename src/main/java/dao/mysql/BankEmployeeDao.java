package dao.mysql;

import dao.interfaces.IDaoBankEmployee;
import model.person.BankEmployee;
import model.person.Department;
import model.person.JobTitle;
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

public class BankEmployeeDao implements IDaoBankEmployee {
    Logger LOGGER = LogManager.getLogger();
    Properties properties = new Properties();
    BankEmployee employee = new BankEmployee();

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
    public void create(BankEmployee bankEmployee) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into bank_employee values (?,?,?,?,?,?) ");
            statement.setInt(1, bankEmployee.getIdEmployee());
            statement.setString(2, bankEmployee.getPersonSurname());
            statement.setString(3, bankEmployee.getPersonName());
            statement.setString(4, bankEmployee.getPhone());
            statement.setInt(5, bankEmployee.getJobTitle().getIdJobTitle());
            statement.setInt(6, bankEmployee.getDepartment().getIdDepartment());
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL query", e);
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from bank_employee where id_employee=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'delete from bank_employee' query", e);
        }
    }

    @Override
    public List<BankEmployee> getAllElements() throws SQLException {
        List<BankEmployee> bankEmployees = new ArrayList<>();
        DepartmentDao depDao = new DepartmentDao();
        JobTitleDao jobDao = new JobTitleDao();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bank_employee");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                BankEmployee bankEmployee = new BankEmployee();
                bankEmployee.setIdEmployee(result.getInt("id_employee"));
                bankEmployee.setPersonSurname(result.getString("surname"));
                bankEmployee.setPersonName(result.getString("name"));
                bankEmployee.setPhone(result.getString("phone"));
                Department dep = depDao.getById((result.getInt("id_department")));
                if (dep != null) {
                    bankEmployee.setDepartment(dep);
                }
                JobTitle job = jobDao.getById((result.getInt("id_job_title")));
                if (job != null) {
                    bankEmployee.setJobTitle(job);
                }

                bankEmployees.add(employee);
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM job_title' query", e);
        }
        return bankEmployees;
    }

    @Override
    public BankEmployee getById(int id) throws SQLException {
        DepartmentDao depDao = new DepartmentDao();
        JobTitleDao jobDao = new JobTitleDao();
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("select * from bank_employee where id_employee = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                employee.setIdEmployee(result.getInt("id_employee"));
                employee.setPersonSurname(result.getString("surname"));
                employee.setPersonName(result.getString("name"));
                employee.setPhone(result.getString("phone"));
                Department dep = depDao.getById((result.getInt("id_department")));
                if (dep != null) {
                    employee.setDepartment(dep);
                }
                JobTitle job = jobDao.getById((result.getInt("id_job_title")));
                if (job != null) {
                    employee.setJobTitle(job);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing select * from bank_employee query", e);
        }
        return employee;
    }

    @Override
    public void updateBankEmployeePhoneBySurname(String surname, String phone) {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("update bank_employee set phone =? where surname =?");
            statement.setString(1, phone);
            statement.setString(2, surname);
            LOGGER.info(statement);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'update BankEmployee phone by surname' query", e);
        }
    }
}
