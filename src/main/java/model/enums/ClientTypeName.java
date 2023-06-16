package model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

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
    @JsonProperty("clientType")
    private final String clientType;

    ClientTypeName(String clientType) {
        this.clientType = clientType;
    }

    public String getClientType() {
        return clientType;
    }

    public static ClientTypeName fromString(String value) {
        for (ClientTypeName typeName : ClientTypeName.values()) {
            if(typeName.getClientType().equals(value)){
                return typeName;
            }
        }
        throw new IllegalArgumentException("Invalid client type: " + value);
    }
}
