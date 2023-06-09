package dao.interfaces;

import model.account.Currency;

public interface IDaoCurrency extends IDao<Currency, Currency>{
    Currency getCurrencyByCode(String code);
}
