import dao.mysql.*;
import model.account.*;
import model.enums.*;
import model.person.*;
import model.products.Credit;
import model.products.CreditType;
import model.products.Deposit;
import model.products.DepositType;
import validation.XMLParserJAXB;
import validation.XMLParserStAX;
import validation.XMLValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.CardService;
import services.DepositService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class BankMain {
    public static void main(String[] args) throws SQLException, ParseException, IOException, JAXBException {
        String xmlFilePath = "d:/SOLVD/bank/bankTest.xml";
        String xmlin = "d:/SOLVD/bank/bankT.xml";

        Logger LOGGER = LogManager.getLogger();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilStartDate = dateFormat.parse("2023-01-06");
        java.util.Date utilEndDate = dateFormat.parse("2023-06-30");
        java.util.Date utilNewStartDate = dateFormat.parse("2024-01-06");
        java.util.Date utilNewEndDate = dateFormat.parse("2024-06-30");

        // Convert java.util.Date to java.sql.Date
        Date startDate = new Date(utilStartDate.getTime());
        Date endDate = new Date(utilEndDate.getTime());
        Date newStartDate = new Date(utilNewStartDate.getTime());
        Date newEndDate = new Date(utilNewEndDate.getTime());

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
        client.create(new Client("TEST_SURNAME", "TEST_NAME", "+564769635",4, "PP6535789","TEST_ADDR", type));
        client.updateClientSurnameByPassport("TEST111", "PP6535789");
        Client cl =  client.getById(2);
        List<Client> clients =client.getAllElements();
        client.remove(4);

        CreditTypeDao creditType = new CreditTypeDao();
        creditType.create(new CreditType(ProductTypeName.EASY_CREDIT, 12.5,6,3,true));
        creditType.updateTermAndPercentByName("Easy Credit", 6,11);
        creditType.getAllElements();
        CreditType typeCred= creditType.getById(1);
        creditType.remove(3);

        DepositTypeDao depositType = new DepositTypeDao();
        depositType.create(new DepositType( ProductTypeName.SAVE_MONEY, 10.00, 18,3, true, false));
        DepositType typeDep=   depositType.getById(2);
        depositType.getAllElements();
        depositType.getDepositTypeByName("START");
        depositType.remove(3);

        DepositDao depDao = new DepositDao();
        depDao.create(new Deposit(2,5555.00, startDate, endDate, typeDep,cl));
        depDao.getById(1);
        depDao.updateDatesByClientID(2,newStartDate, newEndDate);
        depDao.getAllElements();
        depDao.remove(2);

        CreditDao credDao = new CreditDao();
        credDao.create(new Credit(4,2323.23, startDate, endDate, typeCred,cl));
        credDao.getById(1);
        credDao.updateCreditDatesByClientID(4,newStartDate, newEndDate);
        credDao.getAllElements();
        credDao.remove(4);

        SalaryDao salaryDao = new SalaryDao();
        salaryDao.create(new Salary(4,3000.11, 123, bankEmployee));
        salaryDao.updateSalaryByID(4,5432.11);
        salaryDao.getById(1);
        salaryDao.getAllElements();
        salaryDao.remove(4);

        CurrencyDao currencyDao = new CurrencyDao();
        currencyDao.create(new Currency(5,"TEST_NAME","CODE"));
        currencyDao.getCurrencyByCode("USD");
        Currency currency= currencyDao.getById(2);
        currencyDao.getAllElements();
        currencyDao.remove(5);

        OperationTypeDao operationTypeDao = new OperationTypeDao();
        operationTypeDao.create(new OperationType(6, OperationsTypeName.CASH));
        OperationType operationType= operationTypeDao.getById(1);
        operationTypeDao.getAllElements();
        operationTypeDao.getOperationTypeByName("Payment");
        operationTypeDao.remove(6);

        AccountDao accountDao = new AccountDao();
        accountDao.create(new Account(5, "1122334455",startDate, currency,cl ,bankEmployee));
        Account acc = accountDao.getById(1);
       // accountDao.getAllElements();
        accountDao.remove(5);

        CardDao cardDao = new CardDao();
        cardDao.create(new Card(5, CardName.MASTERCARD, startDate, true, 5000,acc ));
        cardDao.updateDayLimitsById(5,7000);
        cardDao.getById(1);
        //cardDao.getAllElements();
        cardDao.remove(5);

        OperationsDao operationsDao = new OperationsDao();
        operationsDao.create(new Operations(5,400,startDate, operationType, acc));
        operationsDao.getById(1);
        operationsDao.updateOperationSumById(5,400.99);
        operationsDao.getAllElements();
        operationsDao.remove(5);

        CardService cardService = new CardService();
        cardService.getCard(1);

        DepositService depositService = new DepositService();
        depositService.getDeposits(1);
        XMLValidation.validateXMLSchema("bank.xsd", "bank.xml");
        XMLParserStAX.parserXMLUsingStAX();
        XMLParserJAXB.unmarshallerXML(xmlFilePath);
      //  XMLParserJAXB.marshallerToXML(clients,xmlin);
    }

}
