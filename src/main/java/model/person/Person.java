package model.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@JsonRootName("client")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Person {
    @XmlElement(name="name")
    @JsonProperty("name")
    private String personName;
    @XmlElement(name="surname")
    @JsonProperty("surname")
    private String personSurname;
    @XmlElement(name="phone")
    @JsonProperty("phone")
    private String phone;

    public Person() {
    }

    public Person(String personName, String personSurname, String phone) {
        this.personName = personName;
        this.personSurname = personSurname;
        this.phone = phone;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
