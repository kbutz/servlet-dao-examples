package com.astontech.bo;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class Phone extends BaseBO {
    private int PhoneId;
    //private int EntityTypeId;
    //private int ClientId;
    //private int PersonId;
    private int AreaCode;
    private int PhoneNumber;
    private int PhoneNumberPost;

    public Phone(){}

    public Phone(int phoneId, int areaCode, int phoneNumber, int phoneNumberPost) {
        PhoneId = phoneId;
        AreaCode = areaCode;
        PhoneNumber = phoneNumber;
        PhoneNumberPost = phoneNumberPost;
    }

    public int getPhoneId() {
        return PhoneId;
    }

    public void setPhoneId(int phoneId) {
        PhoneId = phoneId;
    }

    public int getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(int areaCode) {
        AreaCode = areaCode;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getPhoneNumberPost() {
        return PhoneNumberPost;
    }

    public void setPhoneNumberPost(int phoneNumberPost) {
        PhoneNumberPost = phoneNumberPost;
    }
}
