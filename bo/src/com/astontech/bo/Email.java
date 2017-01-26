package com.astontech.bo;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class Email extends BaseBO{
    //region PROPERTIES
    private int EmailId;
    private String EmailAddress;
    private EntityType EmailType;

    //endregion

    //region CONSTRUCTORS
    public Email(){
        this.EmailType = new EntityType();
    }

    public Email(String emailAddress) {

        this.EmailType = new EntityType();
        EmailAddress = emailAddress;

    }
    //endregion

    //region GETTERS SETTERS

    public int getEmailId() {
        return EmailId;
    }

    public void setEmailId(int emailId) {
        EmailId = emailId;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public EntityType getEmailType() {
        return EmailType;
    }

    public void setEmailType(EntityType emailType) {
        EmailType = emailType;
    }

    //endregion
}
