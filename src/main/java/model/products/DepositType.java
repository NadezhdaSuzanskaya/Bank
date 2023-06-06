package model.products;

import model.enums.ProductTypeName;

public class DepositType extends  BankProductType{
    private int idDeposityType;
    private boolean replenishment;
    private boolean earlywithdrawal;

    public DepositType(ProductTypeName typeName, double persent, int term, int idDeposityType, boolean replenishment, boolean earlywithdrawal) {
        super(typeName, persent, term);
        this.idDeposityType = idDeposityType;
        this.replenishment = replenishment;
        this.earlywithdrawal = earlywithdrawal;
    }

    public int getIdDeposityType() {
        return idDeposityType;
    }

    public void setIdDeposityType(int idDeposityType) {
        this.idDeposityType = idDeposityType;
    }

    public boolean isReplenishment() {
        return replenishment;
    }

    public void setReplenishment(boolean replenishment) {
        this.replenishment = replenishment;
    }

    public boolean isEarlywithdrawal() {
        return earlywithdrawal;
    }

    public void setEarlywithdrawal(boolean earlywithdrawal) {
        this.earlywithdrawal = earlywithdrawal;
    }
}
