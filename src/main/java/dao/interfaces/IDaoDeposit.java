package dao.interfaces;

import model.products.Deposit;

import java.sql.Date;

public interface IDaoDeposit extends IDao<Deposit,Deposit>{
    void updateDatesByClientID(int id, Date beginDate, Date endDate);
}
