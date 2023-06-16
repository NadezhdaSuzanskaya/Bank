TASK:
Create a database schema using Mysql Workbench for the new hierarchy with at least 12 tables and all relations (done)
Create for your hierarchy:
+ 10 statements for insertion. (done)
- 10 statements for updating.
- 10 statements for deletions.
+ 5 alter table.                (done)
+ 1 big statement to join all tables in the database.
+ 5 statements with left, right, inner, outer joins.
+ 7 statements with aggregate functions and group by and without having.
+ 7 statements with aggregate functions and group by and with having.

+ created hierarchy model
+ connected to DB
+ began to create DAO hierarchy
+ created CRUD methods for the class Department

done for the lesson 09/06:
+ created DAO interfaces and classes for hierarchy model
 + for all DAO classes implemented methods: create, remove, getAllElements(), getById(int id)
 + for the class AccountDao  implemented only general methods
 + for the class BankEmployeeDao  implemented method updateBankEmployeePhoneBySurname()
 + for the class CardDao  implemented method updateDayLimitsById()
 + for the class ClientDao  implemented method updateClientSurnameByPassport()
 + for the class ClientTypeDao  implemented method getClientTypeByClientType()
 + for the class CreditDao  implemented method updateCreditDatesByClientID()
 + for the class CreditTypeDao  implemented method updateTermAndPercentByName()
 + for the class CurrencyDao  implemented method getCurrencyByCode()
 + for the class DepartmentDao  implemented method updateDepartmentName()
 + for the class DepositDao  implemented method updateDatesByClientID()
 + for the class DepositTypeDao  implemented method getDepositTypeByName()
 + for the class JobTitleDao  implemented method getJobTitleByName()
 + for the class OperationsDao  implemented method updateOperationSumById()
 + for the class OperationTypeDao  implemented method getOperationTypeByName()
 + for the class SalaryDao  implemented method updateSalaryByID()

 done for the lesson 13/06:
 + added method getCardByAccountID() on the class CardDao
 + added method getDepositByClientID() on the class DepositDao
 + created CardService and DepositService Classes
 + created XML for classes Client, Deposit, DepositType, Credit,CreditType
 + created XSD schema for XML
 + validated xml using XSD (created a class XMLValidation)
 + parsed XML using StaX and wrote result into file

 done for the lesson 16/06:
 +  Parse XML for Clients using JAXB
 + created JSON for for classes Client, Deposit, DepositType, Credit,CreditType

