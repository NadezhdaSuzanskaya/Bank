package model.person;

import model.enums.EmployeeJobTitle;

public class JobTitle {
    private int idJobTitle;
    private EmployeeJobTitle jobName;

    public JobTitle() {
    }

    public JobTitle(int idJobTitle, EmployeeJobTitle jobName) {
        this.idJobTitle = idJobTitle;
        this.jobName = jobName;
    }

    public int getIdJobTitle() {
        return idJobTitle;
    }

    public void setIdJobTitle(int idJobTitle) {
        this.idJobTitle = idJobTitle;
    }

    public EmployeeJobTitle getJobName() {
        return jobName;
    }

    public void setJobName(EmployeeJobTitle jobName) {
        this.jobName = jobName;
    }
}
