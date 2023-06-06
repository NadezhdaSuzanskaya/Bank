package model.person;

public abstract class Person {
    private String personName;
    private String personSurname;
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
