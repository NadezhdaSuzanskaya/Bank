package dao.interfaces;

import model.person.BankEmployee;
import model.person.Department;

import java.sql.SQLException;

public interface IDaoBankEmployee extends IDao<BankEmployee, BankEmployee>{
    void updateBankEmployeePhoneBySurname(String surname, String phone) throws SQLException;
}
