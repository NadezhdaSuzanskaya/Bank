package model.products;

import model.person.Client;

import java.util.Date;

public class Deposit {
    private int idDeposit;
    private double initial_sum;
    private Date start_date;
    private Date end_date;
    private DepositType depositType;
    private Client client;

    public Deposit() {
    }

    public Deposit(int idDeposit, double initial_sum, Date start_date, Date end_date, DepositType depositType, Client client) {
        this.idDeposit = idDeposit;
        this.initial_sum = initial_sum;
        this.start_date = start_date;
        this.end_date = end_date;
        this.depositType = depositType;
        this.client = client;
    }

    public int getIdDeposit() {
        return idDeposit;
    }

    public void setIdDeposit(int idDeposit) {
        this.idDeposit = idDeposit;
    }

    public double getInitial_sum() {
        return initial_sum;
    }

    public void setInitial_sum(double initial_sum) {
        this.initial_sum = initial_sum;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
