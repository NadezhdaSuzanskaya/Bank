package model;

import model.person.Client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bank")
public class Bank {
    private List<Client> client;

    public List<Client> getClients() {
        return client;
    }

    public void setClients(List<Client> clients) {
        this.client = clients;
    }
}
