package validation;

import model.enums.ClientTypeName;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ClientTypeNameAdapter extends  XmlAdapter<String, ClientTypeName> {

    @Override
    public ClientTypeName unmarshal(String value) {
        // Convert the string representation to the corresponding enum value
        return ClientTypeName.valueOf(value.toUpperCase());
    }

    @Override
    public String marshal(ClientTypeName value) {
        // Convert the enum value to its string representation
        return value.name().toLowerCase();
    }
}






