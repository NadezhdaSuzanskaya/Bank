package model.enums;

public enum ProductTypeName {
    EASY_CREDIT("Easy Credit"),
    HOLLIDAY_CREDIT("Credit for holliday"),
    HOT_CREDIT("Hot Credit"),
    BUY_CAR("Car Credit"),
    BUY_REALTY("Realty Credit"),

    ANNUAL_DEPOSIT("Annual Deposit"),
    SUMMER_DEPOSIT("Summer Deposit"),
    SAVE_MONEY("Save Money"),
    START("Start Deposit");


    private final String credit;

    ProductTypeName(String credit) {
        this.credit = credit;
    }

    public String getCredit() {
        return credit;
    }
}
