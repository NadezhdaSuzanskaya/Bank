package dao.interfaces;

import model.account.OperationType;

public interface IDaoOperationType extends IDao<OperationType, OperationType>{
    OperationType getOperationTypeByName(String name);
}
