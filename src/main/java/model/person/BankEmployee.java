package model.person;

public class BankEmployee extends Person{
    private int idEmployee;
    private JobTitle jobTitle;
    private Department department;

    public BankEmployee() {

    }

    public BankEmployee(String personName, String personSurname, String phone, int idEmployee, JobTitle jobTitle, Department department) {
        super(personName, personSurname, phone);
        this.idEmployee = idEmployee;
        this.jobTitle = jobTitle;
        this.department = department;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
