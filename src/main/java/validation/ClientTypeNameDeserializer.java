package validation;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import model.enums.ClientTypeName;

import java.io.IOException;

public class ClientTypeNameDeserializer extends JsonDeserializer<ClientTypeName> {

    @Override
    public ClientTypeName deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getText();
        return ClientTypeName.fromString(value);
    }
}