package dao.mysql;

import dao.interfaces.IDaoBankEmployee;
import model.person.BankEmployee;

import java.sql.SQLException;
import java.util.List;

public class BankEmployeeDao implements IDaoBankEmployee {
    @Override
    public void create(BankEmployee bankEmployee) throws SQLException {

    }

    @Override
    public void remove(int id) throws SQLException {

    }

    @Override
    public List<BankEmployee> getAllElements() throws SQLException {
        return null;
    }

    @Override
    public BankEmployee updateBankEmployeePhoneBySurname(String surname, String phone) throws SQLException {
        return null;
    }
}
