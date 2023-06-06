package dao.sql;

import dao.interfaces.IDaoDepartment;
import model.person.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DepartmentDao implements IDaoDepartment {
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
    public Department getDepartmentByAddress(String address) throws SQLException {
        Department department = new Department();

        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("select * from department where address like ?");
            statement.setString(1, "%" + address + "%");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                department.setIdDepartment(result.getInt("id_department"));
                department.setDepartmentName(result.getString("name"));
                department.setDepartmentAddress(result.getString("address"));
            }
            LOGGER.info("Department address1: " + department.getDepartmentAddress());
        }
        return department;
    }

    @Override
    public Department create(Department dep) throws SQLException {
        Department department = new Department();

        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into department values (?,?,?) ");
            statement.setInt(1, dep.getIdDepartment());
            statement.setString(2, dep.getDepartmentName());
            statement.setString(3, dep.getDepartmentAddress());
            LOGGER.info(statement);
            statement.execute();
        }
        return department;
    }
    @Override
    public Department updateDepartmentName(String name, int id) throws SQLException {
        Department department = new Department();

        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("update department set name=? where id_department=? ");
            statement.setString(1, name);
            statement.setInt(2, id);
            LOGGER.info(statement);
            statement.execute();
        }
        return department;
    }

    @Override
    public void remove( int id) throws SQLException {
        Department department_del = new Department();

        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from department where id_department=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        }
    }


}
