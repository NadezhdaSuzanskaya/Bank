package model.person;

public class Salary {
    private int idSalary;
    private double salarySum;
    private double bonus;
    private BankEmployee bankEmployee;

    public Salary() {
    }

    public Salary(int idSalary, double salarySum, double bonus, BankEmployee bankEmployee) {
        this.idSalary = idSalary;
        this.salarySum = salarySum;
        this.bonus = bonus;
        this.bankEmployee = bankEmployee;
    }

    public int getIdSalary() {
        return idSalary;
    }

    public void setIdSalary(int idSalary) {
        this.idSalary = idSalary;
    }

    public double getSalarySum() {
        return salarySum;
    }

    public void setSalarySum(double salarySum) {
        this.salarySum = salarySum;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public BankEmployee getBankEmployee() {
        return bankEmployee;
    }

    public void setBankEmployee(BankEmployee bankEmployee) {
        this.bankEmployee = bankEmployee;
    }
}
