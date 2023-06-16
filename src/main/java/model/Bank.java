package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import model.person.Client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bank")
@JsonRootName("bank")
public class Bank {
    @JsonProperty("clients")
    private List<Client> client;

    public List<Client> getClients() {
        return client;
    }

    public void setClients(List<Client> client) {
        this.client = client;
    }
}
