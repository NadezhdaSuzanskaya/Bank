package model.account;

import model.account.Account;
import model.enums.CardName;

import java.sql.Date;

public class Card {
    private int idCard;
    private CardName cardName;
    private Date endDate;
    private boolean onlineTransactions;
    private boolean dayLimits;
    private Account account;

    public Card() {
    }

    public Card(int idCard, CardName cardName, Date endDate, boolean onlineTransactions, boolean dayLimits, Account account) {
        this.idCard = idCard;
        this.cardName = cardName;
        this.endDate = endDate;
        this.onlineTransactions = onlineTransactions;
        this.dayLimits = dayLimits;
        this.account = account;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public CardName getCardName() {
        return cardName;
    }

    public void setCardName(CardName cardName) {
        this.cardName = cardName;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isOnlineTransactions() {
        return onlineTransactions;
    }

    public void setOnlineTransactions(boolean onlineTransactions) {
        this.onlineTransactions = onlineTransactions;
    }

    public boolean isDayLimits() {
        return dayLimits;
    }

    public void setDayLimits(boolean dayLimits) {
        this.dayLimits = dayLimits;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
