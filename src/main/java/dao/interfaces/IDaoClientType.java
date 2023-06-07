package dao.interfaces;

import model.person.ClientType;
import model.person.JobTitle;

import java.sql.SQLException;

public interface IDaoClientType extends IDao <ClientType, ClientType>{
    ClientType getClientTypeByClientType(String name) throws SQLException;
}
