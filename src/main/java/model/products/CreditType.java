package model.products;

import model.enums.ProductTypeName;

public class CreditType extends BankProductType{
    private int idCreditType;
    private boolean earalyRepayment;

    public CreditType(ProductTypeName typeName, double persent, int term, int idCreditType, boolean earalyRepayment) {
        super(typeName, persent, term);
        this.idCreditType = idCreditType;
        this.earalyRepayment = earalyRepayment;
    }

    public int getIdCreditType() {
        return idCreditType;
    }

    public void setIdCreditType(int idCreditType) {
        this.idCreditType = idCreditType;
    }

    public boolean isEralyRepayment() {
        return earalyRepayment;
    }

    public void setEralyRepayment(boolean earalyRepayment) {
        this.earalyRepayment = earalyRepayment;
    }
}
