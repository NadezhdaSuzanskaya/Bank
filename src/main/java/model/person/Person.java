package model.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Person {
    @XmlElement(name="name")
    private String personName;
    @XmlElement(name="surname")
    private String personSurname;
    @XmlElement(name="phone")
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
