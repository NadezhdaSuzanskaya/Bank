package model.account;

import java.sql.Date;

public class Operations {
    private int idOperation;
    private double operationSum;
    private Date operationDate;
    private OperationType operationType;
    private Account account;

    public Operations() {
    }

    public Operations(int idOperation, double operationSum, Date operationDate, OperationType operationType, Account account) {
        this.idOperation = idOperation;
        this.operationSum = operationSum;
        this.operationDate = operationDate;
        this.operationType = operationType;
        this.account = account;
    }

    public int getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(int idOperation) {
        this.idOperation = idOperation;
    }

    public double getOperationSum() {
        return operationSum;
    }

    public void setOperationSum(double operationSum) {
        this.operationSum = operationSum;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
