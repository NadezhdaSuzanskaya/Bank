package dao.interfaces;

import model.person.Salary;

import java.sql.Date;

public interface IDaoSalary extends IDao <Salary, Salary>{
    void updateSalaryByID(int id, double salary);
}
