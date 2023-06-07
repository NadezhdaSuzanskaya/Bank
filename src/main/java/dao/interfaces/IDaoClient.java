package dao.interfaces;

import model.person.Client;

import java.sql.SQLException;

public interface IDaoClient extends  IDao<Client, Client>{
    void updateClientSurnameByPassport(String surname, String passport) throws SQLException;
}
