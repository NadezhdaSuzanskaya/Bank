package model.account;

public class Currency {
    private int idCurrency;
    private String currencyName;
    private String currencyCode;

    public Currency() {
    }

    public Currency(int idCurrency, String currencyName, String currencyCode) {
        this.idCurrency = idCurrency;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
    }

    public int getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(int idCurrency) {
        this.idCurrency = idCurrency;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
