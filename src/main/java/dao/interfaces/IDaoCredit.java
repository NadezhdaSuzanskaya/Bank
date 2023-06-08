package dao.interfaces;

import model.products.Credit;

import java.sql.Date;

public interface IDaoCredit extends IDao<Credit, Credit>{
    void updateCreditDatesByClientID(int id, Date beginDate, Date endDate);
}
