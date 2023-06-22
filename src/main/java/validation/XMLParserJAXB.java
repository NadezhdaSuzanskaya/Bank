package validation;

import model.person.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class XMLParserJAXB {

    public static void unmarshallerXML(String xmlFilePath) {
        Logger LOGGER = LogManager.getLogger();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bank.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Bank bank = (Bank) unmarshaller.unmarshal(new FileInputStream(xmlFilePath));
            List<Client> clients = bank.getClients();
            for (Client client : clients) {
                int idClient = client.getIdClient();
                String name = client.getPersonName();
                String surname = client.getPersonSurname();
                String passport = client.getPassport();
                String phone = client.getPhone();
                String address = client.getAddress();

                int idClientType = client.getClientType().getIdClientType();
                String clientTypeName = client.getClientType().getClientType().getClientType();

                LOGGER.info("Client ID: " + idClient +" Name: " + name+" Surname: " + surname+" Passport: " + passport+
                            " Phone: " + phone+" Address: " + address+" Client Type ID: " + idClientType+" Client Type: " + clientTypeName);
            }
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void marshallerToXML(List<Client> clients, String xmlin) throws JAXBException{
        Logger LOGGER = LogManager.getLogger();
        LOGGER.info("Start marshal");
        File inFile = new File(xmlin);

        JAXBContext jaxbContext = JAXBContext.newInstance(Bank.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        Bank bank = new Bank();
        bank.setClients(clients);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(bank, inFile);
        LOGGER.info("Finish marshal");
    }
}
