package dao.interfaces;

import model.account.Card;

public interface IDaoCard extends IDao <Card, Card>{
    void updateDayLimitsById(int id, double dayLimits);
    Card getCardByAccountID(int accountID);
}
