package model.enums;

public enum EmployeeJobTitle {

    ECONOMIST("Economist"),
    ACCOUNTANT("Accountant"),
    CHIEF_SPECIALIST("Chief Specialist"),
    TRAINEE("Trainee");

    private final String employeeJobTitle;

    EmployeeJobTitle(String employeeJobTitle) {
        this.employeeJobTitle = employeeJobTitle;
    }

    public String getEmployeeJobTitle() {
        return employeeJobTitle;
    }
}
