package validation;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

public class XMLParserStAX {
    static Logger LOGGER = LogManager.getLogger();
    static File filein = new File("d:/SOLVD/bank/bank.txt");

    public static String parserXML() throws IOException {
        String xmlFilePath = "d:/SOLVD/bank/bank.xml";
        String stringToFile = "";
        String creditTypeToFile = "";
        String creditToFile = "";
        String depisitTypeToFile = "";
        String depositToFile = "";
        String clientToFile = "";
        boolean withinDepositType = false;
        boolean withinCreditType = false;
        boolean withinClient = false;
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(xmlFilePath));

            String currentElement = "";
            String typeName = "";
            String persent = "";
            String term = "";
            String idCreditType = "";
            String earlyRepayment = "";
            String idDepositType = "";
            String replenishment = "";
            String earlyWithdrawal = "";
            String idDeposit = "";

            String initial_sum = "";
            String start_date = "";
            String end_date = "";
            String idClient = "";
            String name = "";
            String surname = "";
            String phone = "";
            String address = "";
            String passport = "";
            String idClientType = "";
            String idCredit = "";
            String sum = "";
            String clientType = "";


            while (eventReader.hasNext()) {
                XMLEvent ev = eventReader.nextEvent();

                if (ev.isStartElement()) {
                    currentElement = ev.asStartElement().getName().getLocalPart();
                    if (currentElement.equals("deposit")) {
                        withinDepositType = true;
                        withinClient = true;
                    } else if (currentElement.equals("credit")) {
                        withinCreditType = true;
                        withinClient = true;
                    }
                } else if (ev.isCharacters()) {
                    String text = ev.asCharacters().getData().trim();

                    if (!text.isEmpty()) {
                        switch (currentElement) {
                            case "typeName":
                                typeName = text;
                                break;
                            case "persent":
                                persent = text;
                                break;
                            case "term":
                                term = text;
                                break;
                            case "idCreditType":
                                idCreditType = text;
                                break;
                            case "earalyRepayment":
                                earlyRepayment = text;
                                break;
                            case "idDeposityType":
                                idDepositType = text;
                                break;
                            case "replenishment":
                                replenishment = text;
                                break;
                            case "earlywithdrawal":
                                earlyWithdrawal = text;
                                break;
                            case "idDeposit":
                                idDeposit = text;
                                break;
                            case "initial_sum":
                                initial_sum = text;
                                break;
                            case "start_date":
                                start_date = text;
                                break;
                            case "end_date":
                                end_date = text;
                                break;
                            case "idClient":
                                idClient = text;
                                break;
                            case "name":
                                name = text;
                                break;
                            case "surname":
                                surname = text;
                                break;
                            case "passport":
                                passport = text;
                                break;
                            case "phone":
                                phone = text;
                                break;
                            case "address":
                                address = text;
                                break;
                            case "idClientType":
                                idClientType = text;
                                break;
                            case "clientType":
                                clientType = text;
                                break;
                            case "idCredit":
                                idCredit = text;
                                break;
                            case "sum":
                                sum = text;
                                break;
                        }
                    }
                } else if (ev.isEndElement()) {
                    currentElement = ev.asEndElement().getName().getLocalPart();

                    if (currentElement.equals("creditType")) {
                        if (!withinCreditType) {
                            String creditType = "Credit Type:\n" + "IdCreditType: " + idCreditType +
                                    "\nTypeName: " + typeName + "\nPersent: " + persent +
                                    "\nTerm: " + term + "\nEarly Repayment: " + earlyRepayment + "\n\n";
                            LOGGER.info("CreditType :"+creditType);
                            creditTypeToFile = creditTypeToFile + creditType;
                        } else {
                            withinCreditType = false;
                        }
                    } else if (currentElement.equals("depositType")) {
                        if (!withinDepositType) {
                            String depositType = "Deposit Type:\n" + "IdDeposityType: " + idDepositType +
                                    "\nTypeName: " + typeName + "\nPersent: " + persent + "\nTerm: " + term +
                                    "\nReplenishment: " + replenishment + "\nEarly Withdrawal: " + earlyWithdrawal + "\n\n";
                            LOGGER.info("depositType: "+depositType);
                            depisitTypeToFile = depisitTypeToFile + depositType;
                        } else {
                            withinDepositType = false;
                        }
                    } else if (currentElement.equals("deposit")) {
                        String deposit = "Deposit:\n" + "IdDeposit: " + idDeposit + "\nInitial_sum: " + initial_sum +
                                "\nStart_date: " + start_date + "\nEnd_date: " + end_date + "\nIdDepositType: " + idDepositType + "\nTypeName: " + typeName +
                                "\nPercent: " + persent + "\nTerm: " + term + "\nReplenishment: " + replenishment +
                                "\nEarly Withdrawal: " + earlyWithdrawal + "\nIdClient: " + idClient + "\nName: " + name + "\nSurname: " + surname +
                                "\nPassport: " + passport + "\nPhone: " + phone + "\nAddress: " + address + "\nIdClientType: " + idClientType + "\nClientType: " + clientType + "\n\n";
                        LOGGER.info("deposit: "+deposit);
                        depositToFile = depositToFile + deposit;
                    } else if (currentElement.equals("credit")) {
                        String credit = "Credit:\n" + "IdCredit: " + idCredit + "\nSum: " + sum + "\nStart_date: " +
                                start_date + "\nEnd_date: " + end_date + "\nIdCreditType: " + idCreditType +
                                "\nTypeName: " + typeName + "\nPersent: " + persent + "\nTerm: " +
                                term + "\nEarly Repayment: " + earlyRepayment + "\nIdClient: " + idClient + "\nName: " + name + "\nSurname: " + surname +
                                "\nPassport: " + passport + "\nPhone: " + phone + "\nAddress: " + address + "\nIdClientType: " + idClientType + "\nClientType: " + clientType + "\n\n";
                           LOGGER.info("credit:"+credit);
                        creditToFile = creditToFile + credit;

                    } else if (currentElement.equals("client")) {
                        if (!withinClient) {
                            String client = "Client:\n" + "IdClient: " + idClient + "\nName: " + name +
                                    "\nSurname: " + surname + "\nPassport: " + passport + "\nPhone: " + phone +
                                    "\nAddress: " + address + "\nIdClientType: " + idClientType + "\nClientType: " + clientType + "\n\n";
                           LOGGER.info("client: "+client);
                            clientToFile = clientToFile + client;
                        } else {
                            withinClient = false;
                        }
                    }
                }
            }
            eventReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        stringToFile = creditTypeToFile + depisitTypeToFile + depositToFile + creditToFile + clientToFile;
        writeTextInFile(stringToFile);
        return stringToFile;
    }

    public static void writeTextInFile(String textToFile) throws IOException {
        try {
            FileUtils.write(filein, textToFile, Charset.forName("UTF-8"));
            LOGGER.info("Successful writing data to file");
        } catch (IOException exp) {
            LOGGER.error("Problem with writing to the file :" + exp.getMessage());
        }
    }
}
