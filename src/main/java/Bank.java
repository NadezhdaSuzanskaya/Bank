import dao.mysql.DepartmentDao;
import dao.mysql.JobTitleDao;
import model.enums.EmployeeJobTitle;
import model.person.Department;
import model.person.JobTitle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Bank {
    public static void main(String[] args) throws SQLException {
        Logger LOGGER = LogManager.getLogger();

        DepartmentDao dep = new DepartmentDao();
        dep.create(new Department(5, "TEST_NAME", "TEST_ADDR"));
        dep.getDepartmentByAddress("Glebocka");
        dep.updateDepartmentName("TEST_DEPARTMENT",5);
        dep.remove(5);
        dep.getAllElements();

        JobTitleDao jobTitle = new JobTitleDao();
        jobTitle.create(new JobTitle(5, EmployeeJobTitle.ACCOUNTANT));
        jobTitle.remove(5);
        jobTitle.getAllElements();
        jobTitle.getJobTitleByName("Trainee");

    }

}
