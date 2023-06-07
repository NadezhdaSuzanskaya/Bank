package dao.mysql;

import dao.interfaces.IDaoJobTitle;
import model.enums.EmployeeJobTitle;
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
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class JobTitleDao implements IDaoJobTitle {
    Logger LOGGER = LogManager.getLogger();
    Properties properties = new Properties();
    JobTitle jobTitle = new JobTitle();

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
    public void create(JobTitle jobTitle) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("insert into job_title values (?,?) ");
            statement.setInt(1, jobTitle.getIdJobTitle());
            statement.setString(2, jobTitle.getJobName().getEmployeeJobTitle());
            LOGGER.info(statement);
            statement.execute();
        }
        catch (SQLException e) {
            LOGGER.error("Error executing SQL query", e);
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("delete from job_title where id_job_title=? ");
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info(statement);
        }
        catch (SQLException e) {
            LOGGER.error("Error executing SQL 'delete from job_title' query", e);
        }
    }

    @Override
    public List<JobTitle> getAllElements() throws SQLException {
        List<JobTitle> jobList = new ArrayList<>(); // Create a list  for departments
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM job_title");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                JobTitle jobTitle = new JobTitle();
                jobTitle.setIdJobTitle(result.getInt("id_job_title"));
                String jobNameString = result.getString("name").trim();
                for (EmployeeJobTitle enumValue : EmployeeJobTitle.values()) {
                    if (enumValue.getEmployeeJobTitle().equalsIgnoreCase(jobNameString)) {
                        jobTitle.setJobName(enumValue);
                        break;
                    }
                }
                jobList.add(jobTitle);
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'SELECT * FROM job_title' query", e);
        }
        LOGGER.info(jobList);
        return jobList;
    }

    @Override
    public JobTitle getById(int id) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("select * from job_title where id_job_title = ?");
            statement.setInt(1, id );
         //   LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                jobTitle.setIdJobTitle(result.getInt("id_job_title"));
                String jobNameString = result.getString("name").trim();
                for (EmployeeJobTitle enumValue : EmployeeJobTitle.values()) {
                    if (enumValue.getEmployeeJobTitle().equalsIgnoreCase(jobNameString)) {
                        jobTitle.setJobName(enumValue);
                        break;
                    }
                }
            }
        }
        catch (SQLException e) {
            LOGGER.error("Error executing SQL query", e);
        }
        return jobTitle;
    }

    @Override
    public JobTitle getJobTitleByName(String name) throws SQLException {
        loadProperties();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"))) {
            PreparedStatement statement = connection.prepareStatement("select * from job_title where name like ?");
            statement.setString(1, "%" + name + "%");
            LOGGER.info(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                // JobTitle jobTitle = new JobTitle();
                jobTitle.setIdJobTitle(result.getInt("id_job_title"));
                String jobNameString = result.getString("name").trim();
                for (EmployeeJobTitle enumValue : EmployeeJobTitle.values()) {
                    if (enumValue.getEmployeeJobTitle().equalsIgnoreCase(jobNameString)) {
                        jobTitle.setJobName(enumValue);
                        break;
                    }
                }

            }
            LOGGER.info("Job_title id: " + jobTitle.getIdJobTitle() + "Job_title name: " + jobTitle.getJobName());
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL query", e);
        }
        return jobTitle;
    }

}
