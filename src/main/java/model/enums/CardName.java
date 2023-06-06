package model.enums;

public enum CardName {
    VISA("Visa"),
    MASTERCARD("MasterCard");

    private final String cardName;

    CardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return cardName;
    }
}
