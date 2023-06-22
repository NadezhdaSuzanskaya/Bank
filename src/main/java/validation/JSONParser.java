package validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import model.enums.ClientTypeName;
import model.person.Client;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class JSONParser  {
    static Logger LOGGER = LogManager.getLogger();
    public static void deserialized(File jsonFile) throws IOException {
        String json =readFromFile(jsonFile);
        try {
            LOGGER.info("Start to deserialize");
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(ClientTypeName.class, new ClientTypeNameDeserializer());
            mapper.registerModule(module);

            BankWrapper bank = mapper.readValue(json, BankWrapper.class);

            for (Client client : bank.getBank().getClients()) {
                LOGGER.info("\nIdClient: " + client.getIdClient()+" Name: " + client.getPersonName()+" Surname: " + client.getPersonSurname()+
                        " Passport: " + client.getPassport()+" Phone: " + client.getPhone()+" Address: " + client.getAddress()+
                        " Client Type ID: " + client.getClientType().getIdClientType()+" Client Type: " + client.getClientType().getClientType().getClientType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("Finish to deserialize");
    }
    public static String readFromFile(File fileout) throws IOException {
        String content = "";
        try {
            content = FileUtils.readFileToString(fileout, Charset.forName("UTF-8"));
            System.out.println(content);
            LOGGER.info("File info:" + content);

        } catch (IOException exp) {
            LOGGER.error("Problem with reading the file :" + exp.getMessage());
        }
        return content;
    }
    public static void serialized(List<Client> client, String xmlin) throws IOException {
        LOGGER.info("Start to serialize");
        ObjectMapper objectMapper = new ObjectMapper();
        File inFile = new File(xmlin);

        Bank bank = new Bank();
        bank.setClients(client);

        // Create a BankWrapper and set the Bank object
        BankWrapper bankWrapper = new BankWrapper(bank);

        // Write the BankWrapper to JSON file
        objectMapper.writeValue( inFile,bankWrapper);
        LOGGER.info("Finish to serialize");
    }
}
