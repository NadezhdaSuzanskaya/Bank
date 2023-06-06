package model.account;

import model.enums.OperationsTypeName;

public class OperationType {
    private int idOperationType;
    private OperationsTypeName operationTypeName;

    public OperationType() {
    }

    public OperationType(int idOperationType, OperationsTypeName operationTypeName) {
        this.idOperationType = idOperationType;
        this.operationTypeName = operationTypeName;
    }

    public int getIdOperationType() {
        return idOperationType;
    }

    public void setIdOperationType(int idOperationType) {
        this.idOperationType = idOperationType;
    }

    public OperationsTypeName getOperationTypeName() {
        return operationTypeName;
    }

    public void setOperationTypeName(OperationsTypeName operationTypeName) {
        this.operationTypeName = operationTypeName;
    }
}
