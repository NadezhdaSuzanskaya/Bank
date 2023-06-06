package model.enums;

public enum ClientTypeName {
    INDIVIDUAL("Individual"),
    CJSC("CJSC"),
    LLC("LLC"),
    CORPORATION("Corporation");

    private final String clientType;

    ClientTypeName(String clientType) {
        this.clientType = clientType;
    }

    public String getClientType() {
        return clientType;
    }
}
