package model.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="client")
@JsonRootName("client")
@XmlSeeAlso(Person.class)
public class Client extends Person{
    @XmlAttribute(name="idClient")
    @JsonProperty("idClient")
    private int idClient;

    @XmlElement(name="passport")
    @JsonProperty("passport")
    private String passport;

    @XmlElement(name="address")
    @JsonProperty("address")
    private String address;

    @XmlElement(name="clientType")
    @JsonProperty("clientType")
    private ClientType clientType;

    public Client() {

    }

    public Client(String personName, String personSurname, String phone, int idClient, String passport, String address, ClientType clientType) {
        super(personName, personSurname, phone);
        this.idClient = idClient;
        this.passport = passport;
        this.address = address;
        this.clientType = clientType;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
}
