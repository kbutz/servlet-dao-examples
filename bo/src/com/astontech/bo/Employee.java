package com.astontech.bo;

import java.util.Date;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class Employee extends Person {

    // PARAMETERS/ATTRIBUTES/FIELDS
    private int EmployeeId;
    private Date HireDate;
    private Date TermDate;

    // CONSTRUCTORS
    public Employee(){}

    public Employee(String firstName, String lastName) {
        // super.setFirstName(firstName) would refer to parent class method
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public void setEmployeeId(int employeeId) {EmployeeId = employeeId;}
    public int getEmployeeId() {return EmployeeId;}
    public void setHireDate(Date hireDate) {HireDate = hireDate;}
    public Date getHireDate() {return HireDate; }
    public void setTermDate (Date termDate) { TermDate = termDate;}
    public Date getTermDate () {return TermDate;}
}
