package model.account;

import model.person.BankEmployee;
import model.person.Client;

import java.sql.Date;

public class Account {
    private int idAccount;
    private String account;
    private Date startDate;
    private Currency currency;
    private Client client;
    private BankEmployee bankEmployee;

    public Account() {
    }

    public Account(int idAccount, String account, Date startDate, Currency currency, Client client, BankEmployee bankEmployee) {
        this.idAccount = idAccount;
        this.account = account;
        this.startDate = startDate;
        this.currency = currency;
        this.client = client;
        this.bankEmployee = bankEmployee;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BankEmployee getBankEmployee() {
        return bankEmployee;
    }

    public void setBankEmployee(BankEmployee bankEmployee) {
        this.bankEmployee = bankEmployee;
    }
}
