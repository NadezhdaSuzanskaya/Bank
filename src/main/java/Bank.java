import dao.mysql.*;
import model.enums.ClientTypeName;
import model.enums.EmployeeJobTitle;
import model.person.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Bank {
    public static void main(String[] args) throws SQLException {
        Logger LOGGER = LogManager.getLogger();

        DepartmentDao dep = new DepartmentDao();
        dep.create(new Department(5, "TEST_NAME", "TEST_ADDR"));
        Department departmentByAddress= dep.getDepartmentByAddress("Glebocka");
        dep.updateDepartmentName("TEST_DEPARTMENT",5);
        dep.remove(5);
        dep.getAllElements();

        JobTitleDao jobTitle = new JobTitleDao();
        jobTitle.create(new JobTitle(5, EmployeeJobTitle.ACCOUNTANT));
        jobTitle.remove(5);
        jobTitle.getAllElements();
        JobTitle jobTitleByName=  jobTitle.getJobTitleByName("Trainee");

        BankEmployeeDao employee = new BankEmployeeDao();
        employee.create(new BankEmployee( "TEST_SURNAME","TEST_NAME","11111111",5,jobTitleByName,departmentByAddress));
        employee.remove(5);
        employee.updateBankEmployeePhoneBySurname("Smidt", "+12121212");
        employee.getAllElements();
        BankEmployee bankEmployee = employee.getById(1);


        ClientTypeDao clientType = new ClientTypeDao();
        clientType.create(new ClientType(4, ClientTypeName.CORPORATION));
        ClientType type= clientType.getClientTypeByClientType("Business");
        clientType.getById(1);
        clientType.getAllElements();
        clientType.remove(4);

        ClientDao client = new ClientDao();
      //  client.create(new Client("TEST_SURNAME", "TEST_NAME", "+564769635",4, "PP6535789","TEST_ADDR", type));
        client.updateClientSurnameByPassport("TEST111", "PP6535789");
        client.getById(1);
        client.getAllElements();
        client.remove(4);
    }

}
