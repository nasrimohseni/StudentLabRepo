package af.kardan;

import androidx.annotation.NonNull;

public class Employee {

    private int employeeID;
    private String employeeName, employeeFatherName, employeeDesignation, employeeSalary;

    public Employee(int employeeID, String employeeName, String employeeFatherName, String employeeDesignation, String employeeSalary) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeFatherName = employeeFatherName;
        this.employeeDesignation = employeeDesignation;
        this.employeeSalary = employeeSalary;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeFatherName() {
        return employeeFatherName;
    }

    public void setEmployeeFatherName(String employeeFatherName) {
        this.employeeFatherName = employeeFatherName;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    public String getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(String employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    @NonNull
    @Override
    public String toString() {
        return employeeName;
    }
}
