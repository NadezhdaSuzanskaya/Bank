package model.enums;

public enum OperationsTypeName {
    REPLENISHMENT("Deposit replenishment"),
    REPAYMENT("Loan repayment"),
    PAYMENT("Payment"),
    TRANSFER("Transfer"),
    REFILL("Refill");

    private final String operationsTypeName;

    OperationsTypeName(String operationsTypeName) {
        this.operationsTypeName = operationsTypeName;
    }

    public String getOperationsTypeName() {
        return operationsTypeName;
    }
}
