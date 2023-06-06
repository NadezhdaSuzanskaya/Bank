package model.person;

public class Department {
    private int idDepartment;
    private String departmentName;
    private String departmentAddress;

    public Department() {
    }

    public Department(int idDepartment, String departmentName, String departmentAddress) {
        this.idDepartment = idDepartment;
        this.departmentName = departmentName;
        this.departmentAddress = departmentAddress;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }
}
