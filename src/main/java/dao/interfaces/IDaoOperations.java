package dao.interfaces;

import model.account.Operations;

public interface IDaoOperations extends IDao <Operations, Operations>{
    void updateOperationSumById(int id, double sum);
}
