package model.person;

import model.enums.ClientTypeName;

public class ClientType {
    private int idClientType;
    private ClientTypeName clientType;

    public ClientType() {
    }

    public ClientType(int idClientType, ClientTypeName clientType) {
        this.idClientType = idClientType;
        this.clientType = clientType;
    }

    public int getIdClientType() {
        return idClientType;
    }

    public void setIdClientType(int idClientType) {
        this.idClientType = idClientType;
    }

    public ClientTypeName getClientType() {
        return clientType;
    }

    public void setClientType(ClientTypeName clientType) {
        this.clientType = clientType;
    }

}
