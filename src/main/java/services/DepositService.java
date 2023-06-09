package services;

import dao.mysql.AccountDao;
import dao.mysql.CardDao;
import dao.mysql.ClientDao;
import dao.mysql.DepositDao;
import model.person.Client;
import model.products.Deposit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class DepositService {
    public Deposit getDeposits(int clientID)
    {       Logger LOGGER = LogManager.getLogger();
        Deposit deposit = new DepositDao().getDepositByClientID(clientID);
        try {
            Client client = new ClientDao().getById(clientID);
            deposit.setClient(client);
        } catch (SQLException e) {
            LOGGER.error("Error executing DepositService" , e);
        }
        LOGGER.info("Client name: "+deposit.getClient().getPersonName()+" "+deposit.getClient().getPersonSurname()+
                    "Deposit sum: "+deposit.getInitial_sum());
        return deposit;
    }
}
