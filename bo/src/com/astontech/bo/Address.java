package com.astontech.bo;

import java.util.Date;

public class Address extends BaseBO {
    private int AddressId;
    //private int ClientId;
    //private int PersonId;
    //private int EntityTypeId;
    private int AddressNumber;
    private String Street;
    private String Street02;
    private String City;
    private int StateId;
    private int CountryId;
    private Date DateCreate;

    public Address(){}

    public Address(int addressId, int addressNumber, String street, String street02, String city, int stateId, int countryId, Date dateCreate) {
        AddressId = addressId;
        AddressNumber = addressNumber;
        Street = street;
        Street02 = street02;
        City = city;
        StateId = stateId;
        CountryId = countryId;
        DateCreate = dateCreate;
    }


    public int getAddressId() {
        return AddressId;
    }

    public void setAddressId(int addressId) {
        AddressId = addressId;
    }

    public int getAddressNumber() {
        return AddressNumber;
    }

    public void setAddressNumber(int addressNumber) {
        AddressNumber = addressNumber;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getStreet02() {
        return Street02;
    }

    public void setStreet02(String street02) {
        Street02 = street02;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getStateId() {
        return StateId;
    }

    public void setStateId(int stateId) {
        StateId = stateId;
    }

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    public Date getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        DateCreate = dateCreate;
    }

    public String printFullAddress(){
        return AddressNumber + " " + Street + " " + Street02;
    }
}
