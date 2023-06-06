package model.products;

import model.person.Client;

import java.util.Date;

public class Credit {
    private int idCredit;
    private double initial_sum;
    private Date start_date;
    private Date end_date;
    private CreditType creditType;
    private Client client;

    public Credit() {
    }

    public Credit(int idCredit, double initial_sum, Date start_date, Date end_date, CreditType creditType, Client client) {
        this.idCredit = idCredit;
        this.initial_sum = initial_sum;
        this.start_date = start_date;
        this.end_date = end_date;
        this.creditType = creditType;
        this.client = client;
    }

    public int getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
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

    public CreditType getCreditType() {
        return creditType;
    }

    public void setCreditType(CreditType creditType) {
        this.creditType = creditType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
