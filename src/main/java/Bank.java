import dao.sql.DepartmentDao;
import model.person.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Bank {
    public static void main(String[] args) throws SQLException {

        Logger LOGGER = LogManager.getLogger();
        new DepartmentDao().create(new Department(5, "TEST_NAME", "TEST_ADDR"));
        new DepartmentDao().getDepartmentByAddress("Glebocka");
        new DepartmentDao().updateDepartmentName("TEST_DEPARTMENT",5);
        new DepartmentDao().remove(5);
    }

}
