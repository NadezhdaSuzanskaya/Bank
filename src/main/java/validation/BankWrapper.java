package validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.Bank;

public class BankWrapper {
    @JsonProperty("bank")
    private Bank bank;

    public BankWrapper() {
    }

    public BankWrapper(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}