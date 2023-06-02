 
/*-----------------5 alter table--------------------------*/
 ALTER TABLE operation_type DROP COLUMN operation_typecol;
 ALTER TABLE credittype RENAME COLUMN id_creditType to id_credit_type;
 ALTER TABLE account ADD UNIQUE INDEX id_account_UNIQUE (id_account ASC) VISIBLE;
 ALTER TABLE salary RENAME COLUMN id_bank_employee to id_employee;
 ALTER TABLE deposit_type MODIFY COLUMN replenishment int;
 ALTER TABLE deposit_type MODIFY COLUMN replenishment BOOLEAN;
 ALTER TABLE deposit_type DROP COLUMN description;
 ALTER TABLE salary RENAME COLUMN id_bank_employee to id_employee;
 
 /*-----------------inserts--------------------------*/
insert into client_type values (1, "Individual");
insert into client_type values (2, "Business");

insert into deposit_type values  (1, "START",5,36,1,0);
insert into deposit_type values  (2, "SUMMER",57,3,1,1);

insert into deposit values  (1, 100,"2023-05-25","2023-08-25",1,1);

insert into client values  (1, "Jhon","NB2323536","+235354646","NY",1);
insert into client values  (2, "Doris","SD4654757","+747536457","Warsaw",1);
insert into client values  (3, "Alex","QW87677777","+7657354657","Oslo",2);

insert into credittype values  (1, "HOT","Account in USD, EUR",13,18);
insert into credittype values  (2, "BUY CAR","Account in USD, EUR",15,24);

insert into credit values  (1, 10000,"2023-06-01","2024-06-30",1,2);
insert into credit values  (3, 5000,"2023-06-01","2024-06-30",1,2);
insert into credit values  (2, 13000,"2023-05-21","2025-06-30",2,1);

insert into currency values (1, "Dollar USA","USD");
insert into currency values (2, "EURO","EUR");
insert into currency values (3, "Polish zloty","PLN");
insert into currency values (4, "UK Pound","GBP");
  
insert into operation_type values (1, "Deposit replenishment");
insert into operation_type values (2, "Loan repayment");
insert into operation_type values (3, "Payment");
insert into operation_type values (4, "Transfer");
insert into operation_type values (5, "Refill");

insert into department values (1, "Department 1","Warszawa, ul. Glebocka 15");
insert into department values (2, "Department 2","Warszawa, ul. Ostródzka 55");
insert into department values (3, "Department 3","Warszawa, ul. Chmielna 26");
insert into department values (4, "Department 4","Warszawa, ul. Marszałkowska 13");

insert into job_title values (1, "Economist");
insert into job_title values (2, "Accountant");
insert into job_title values (3, "Chief Specialist");
insert into job_title values (4, "Trainee");

insert into salary values (1,1, 10000, 100);
insert into salary values (2,1, 12350, 90);
insert into salary values (3,2, 9899, 341);

insert into bank_employee values (1, "Smidt", "Tom","+54345654",1,2);
insert into bank_employee values (2, "Lele", "Adam","+32542345",2,2);
insert into bank_employee values (3, "Dark", "Monika","+1111111",1,3);
insert into bank_employee values (4,"Silver", "David","+33465475",3,1);

insert into account values (1, 1234567890, "2023-01-01", 1,1,1);
insert into account values (2, 1212121212, "2023-04-21", 2,1,2);
insert into account values (3, 3232432323, "2023-03-18", 1,3,3);
insert into account values (4, 5434645663, "2023-06-11", 3,2,1);

insert into card values (1, "Visa", "2025-06-11", true,1000, 2);
insert into card values (2, "MasterCard", "2025-08-20",true, 15000,2);
insert into card values (3, "Card", "2025-01-01",false, 5000,1);
insert into card values (4, "Visa", "2024-11-22", true, 10000,1);

insert into operations values (1, -10, "2023-05-26", 3,1);
insert into operations values (2, 90, "2023-05-27", 1,1);
insert into operations values (3, -120, "2023-05-28", 2,2);
insert into operations values (4, -100, "2023-05-29", 3,2);

 /*-----------------updates--------------------------*/

update credit SET sum = 12999 WHERE id_credit=2;
update credit set sum = 10500 where id_client=(select id_client from client where fio ="Jhon");
                                              
update salary set bonus = 250 where id_bank_employee in
		(select id_employee from bank_employee inner join job_title on bank_employee.id_job_title=job_title.id_job_title
												where job_title.name='Economist');
update card set day_limits = 200  where name like "%Card";			

select id_account from account inner join client on client.id_client=account.id_client
                               inner join currency on currency.id_currency=account.id_currency
                               where currency.currency_code='USD' and client.passport="QW87677777";

update card set online_transactions = false where name ="Visa" and id_account =(select id_account from account inner join client on client.id_client=account.id_client
                               inner join currency on currency.id_currency=account.id_currency
                               where currency.currency_code='USD' and client.passport="QW87677777");	
  
  /*---------------statements with left, right, inner, outer joins---------------------*/
select account, card.name from account left join card on card.id_account=account.id_account;
select account, card.name from account left join card on card.id_account=account.id_account;
select account, currency_code from account right join  currency on currency.id_currency=account.id_currency;
select account, currency_code from account full join currency;
  
select id_employee from bank_employee inner join job_title on bank_employee.id_job_title=job_title.id_job_title
												where job_title.name='Economist';
                                                
select client.name,client.surname , account.account  from account inner join client on client.id_client=account.id_client
                               inner join currency on currency.id_currency=account.id_currency
                               where currency.currency_code='USD' and client.passport="QW87677777";
                               
/*----------------------1 big statement to join all tables in the database.    ----------------*/                      
select concat (client.name," " ,client.surname) as client_fio, account.account,  deposit_type.name, deposit.initial_amount,  currency_code, card.name,
         operations.sum,  operation_type.name as 'operation type', concat(bank_employee.name," " , bank_employee.surname)  as 'bank employee fio',
         department.name as department, job_title.name as job_title, salary.salary_sum
																	 from account inner join client on client.id_client=account.id_client
                                                                                  inner join currency on currency.id_currency=account.id_currency
                                                                                  inner join card on card.id_account=account.id_account
                                                                                  inner join operations on operations.id_account=account.id_account
																				  inner join operation_type on operations.id_operation_type=operation_type.id_operation_type
																				  inner join bank_employee on bank_employee.id_employee=account.id_employee
                                                                                  inner join department on bank_employee.id_department=department.id_department
                                                                                  inner join job_title on bank_employee.id_job_title=job_title.id_job_title
                                                                                  inner join salary on bank_employee.id_employee=salary.id_employee
                                                                                  inner join deposit on client.id_client=deposit.id_client
                                                                                  inner join deposit_type on deposit_type.id_deposit_type=deposit.id_deposit_type
                                                                                  where operation_type.name = "Deposit replenishment"
union all
select concat (client.name," " ,client.surname) as client_fio, account.account, credit_type.name, credit.sum,  currency_code, card.name,
         operations.sum,  operation_type.name as 'operation type', concat(bank_employee.name," " , bank_employee.surname) as 'bank employee fio',
         department.name as department, job_title.name as job_title, salary.salary_sum
																	 from account inner join client on client.id_client=account.id_client
                                                                                  inner join currency on currency.id_currency=account.id_currency
                                                                                  inner join card on card.id_account=account.id_account
                                                                                  inner join operations on operations.id_account=account.id_account
																				  inner join operation_type on operations.id_operation_type=operation_type.id_operation_type
																				  inner join bank_employee on bank_employee.id_employee=account.id_employee
                                                                                  inner join department on bank_employee.id_department=department.id_department
                                                                                  inner join job_title on bank_employee.id_job_title=job_title.id_job_title
                                                                                  inner join salary on bank_employee.id_employee=salary.id_employee
                                                                                  inner join credit on client.id_client=credit.id_client
                                                                                  inner join credit_type on credit_type.id_credit_type=credit.id_credit_type
                                                                                  where operation_type.name != "Deposit replenishment";
 
 /*-------------------------7 statements with aggregate functions and group by and without having---------------------------------- */                                                                                
select sum(sum), account.account from operations inner join account on account.id_account=operations.id_account group by account.account;
select min(sum), max(sum), operation_type.name  from operations inner join operation_type on  operations.id_operation_type=operation_type.id_operation_type
                                                group by operations.id_operation_type;
select count(*) ,name from card  group by name;      
select concat(bank_employee.name," " , bank_employee.surname) fio, bonus from bank_employee inner join salary on bank_employee.id_employee=salary.id_employee 
																		    where  salary.bonus <(select avg(bonus) from salary); 
                                                                            
select concat(bank_employee.name," " , bank_employee.surname) fio, bonus from bank_employee inner join salary on bank_employee.id_employee=salary.id_employee 
																		    where  salary.bonus =(select max(bonus) from salary); 
select count(account.account) ,currency_name from account right join currency on account.id_currency=currency.id_currency group by currency_name; 

select avg(sum) from credit group by id_client;

 /*-------------------------7 statements with aggregate functions and group by and  with having.---------------------------------- */                                
select min(sum), max(sum), operation_type.name  from operations inner join operation_type on  operations.id_operation_type=operation_type.id_operation_type
                                                group by operations.id_operation_type having operation_type.name='Payment';
select  count(*) ,name, day_limits from card  group by name,day_limits  having day_limits<5000;    
select currency_name, account.start_date from account inner join currency on account.id_currency=currency.id_currency 
                                         group by currency_name, account.start_date having account.start_date>'2023-04-01';      
                                
select count(id_employee) from bank_employee inner join job_title on bank_employee.id_job_title=job_title.id_job_title
											group by job_title.name having job_title.name='Economist';
select avg(sum), min(sum), max(sum)  from credit group by id_client   having id_client =2;     
select sum(sum), concat(client.name," " , client.surname) fio  from credit inner join client on client.id_client=credit.id_client  group by fio ;   
select count(*) ,name from card  group by name,online_transactions  having online_transactions is true; 

 /*-----------------deletes--------------------------*/
  insert into card values (5, "Test", "2024-11-22", true, 10000,1);
  delete from card where name ="Test";   
  
   insert into card values (5, "Test1", "2023-01-22", true, 10000,1);
   insert into card values (6, "Test2", "2023-02-11", true, 10000,1);
   insert into card values (7, "Test3", "2023-05-05", true, 10000,1);
   insert into card values (8, "Test4", "2023-07-10", true, 10000,1);
   delete from card where end_date <"2024-01-01"; 
   
   insert into salary values (4,4, 1000, 500);
   delete from salary where id_employee=(select id_employee from bank_employee where surname = "Silver");
   
   insert into salary values (4,4, 1000, 500);
   delete from salary where bonus=500 and salary_sum=1000;
   
   insert into deposit values  (2, 888,"2024-05-25","2024-08-25",1,2);  
   insert into deposit values  (3, 1000,"2024-05-25","2024-08-25",1,2);   
   delete from deposit where initial_amount>500;
                              

                                                                                 


  



                                                




