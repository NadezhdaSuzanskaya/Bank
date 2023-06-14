package model.enums;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="clientType")
public enum ClientTypeName {
    INDIVIDUAL("Individual"),
    CJSC("CJSC"),
    LLC("LLC"),
    CORPORATION("Corporation");
    @XmlElement(name="clientType")
    private final String clientType;

    ClientTypeName(String clientType) {
        this.clientType = clientType;
    }

    public String getClientType() {
        return clientType;
    }
}
