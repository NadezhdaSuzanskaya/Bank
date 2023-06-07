package dao.interfaces;

import model.products.CreditType;

public interface IDaoCreditType extends IDao <CreditType, CreditType>{
    void updateTermAndPercentByName(String name, int term, int percent);
}
