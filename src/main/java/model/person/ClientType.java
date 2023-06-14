package model.person;

import model.enums.ClientTypeName;
import validation.ClientTypeNameAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="clientType")

public class ClientType {
    @XmlAttribute(name="idClientType")
    private int idClientType;
    @XmlJavaTypeAdapter(ClientTypeNameAdapter.class)
    @XmlElement(name="clientType")
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
