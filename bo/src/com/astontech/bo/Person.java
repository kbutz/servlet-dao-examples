package com.astontech.bo;

import common.helpers.StringHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person extends BaseBO implements Comparable, Serializable{
    //region PROPERTIES
    private int PersonId;
    private String Title;
    private String FirstName;
    private String MiddleName;
    private String LastName;
    private String DisplayFirstName;
    private String Gender;
    private List<Email> Emails;
    private Date BirthDate;
    //private static final long serialVersionUID = 543609312;
    //notes:    transient prevents Serialize from accessing variable
    //private transient String SSN
    //endregion
    //region Constructors
    public Person() {
        this.Emails = new ArrayList<>();
    }
    //endregion
    //region GETTERS AND SETTERS
    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        PersonId = personId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDisplayFirstName() {
        return DisplayFirstName;
    }

    public void setDisplayFirstName(String displayFirstName) {
        DisplayFirstName = displayFirstName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public List<Email> getEmails() {
        return Emails;
    }

    public void setEmails(List<Email> emails) {
        Emails = emails;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    //endregion
    //region Custom Methods
    public String GetFullName() {
        if (StringHelper.isNullOrEmpty(this.FirstName) && StringHelper.isNullOrEmpty(this.LastName)){
            return "No name set.";
        } else {
            if(StringHelper.isNullOrEmpty(this.FirstName))
                return this.LastName;
            else if (StringHelper.isNullOrEmpty(this.LastName))
                return this.FirstName;
            else
                return this.FirstName + " " + this.LastName;
        }
    }

    //Compares Person object by PersonId
    @Override
    public int compareTo(Object o) {
        Person other = (Person) o;

        if (getPersonId() > other.getPersonId())
            return 1;
        else if  (getPersonId() < other.getPersonId())
            return -1;
        else
            return 0;
    }

    public String ToString() {
        return "PersonId= " + this.PersonId + ") Name: " + this.getFirstName() + ", DOB: " + this.BirthDate;
    }
    //endregion
}












