package dao.interfaces;

import model.person.ClientType;
import model.products.DepositType;

import java.sql.SQLException;

public interface IDaoDepositType extends IDao <DepositType, DepositType>{
    DepositType getDepositTypeByName(String name) throws SQLException;

}
