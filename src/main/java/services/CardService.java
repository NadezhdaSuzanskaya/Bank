package services;

import dao.mysql.AccountDao;
import dao.mysql.CardDao;
import model.account.Account;
import model.account.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class CardService {
    public Card getCard(int accountID)
    {       Logger LOGGER = LogManager.getLogger();
        Card card = new CardDao().getCardByAccountID(accountID);
        try {

            Account account = new AccountDao().getById(accountID);
            card.setAccount(account);
        } catch (SQLException e) {
            LOGGER.error("Error executing SQL 'update salary by id' query", e);
        }
        LOGGER.info("Card name: "+card.getCardName()+"Account value: "+card.getAccount().getAccount());
        return card;
    }
}
