package model.person;

public class Client extends Person{
    private int idClient;
    private String passport;
    private String address;
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
