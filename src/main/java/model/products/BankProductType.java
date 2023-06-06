package model.products;

import model.enums.ProductTypeName;

public abstract class BankProductType {
    private ProductTypeName typeName;
    private double persent;
    private int term;

    public BankProductType() {
    }

    public BankProductType(ProductTypeName typeName, double persent, int term) {
        this.typeName = typeName;
        this.persent = persent;
        this.term = term;
    }

    public ProductTypeName getTypeName() {
        return typeName;
    }

    public double getPersent() {
        return persent;
    }

    public int getTerm() {
        return term;
    }

    public void setTypeName(ProductTypeName typeName) {
        this.typeName = typeName;
    }

    public void setPersent(double persent) {
        this.persent = persent;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
