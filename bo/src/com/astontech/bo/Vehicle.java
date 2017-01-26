package com.astontech.bo;

import java.util.Date;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class Vehicle extends BaseBO implements Comparable{
    //region PROPERTIES
    private int VehicleId;
    private int Year;
    private String LicensePlate;
    private String Vin;
    private String Color;
    private boolean IsPurchase;
    private int PurchasePrice;
    private Date PurchaseDate;
    private VehicleModel VehicleMakeAndModel;
    //endregion

    //region CONSTRUCTORS
    public Vehicle(){
        this.VehicleMakeAndModel = new VehicleModel();
    }
    public Vehicle(int vehicleId, int year, VehicleModel vehicleMakeAndModel){
        this.VehicleMakeAndModel = vehicleMakeAndModel;
        VehicleId = vehicleId;
        Year = year;
    }
    public Vehicle(int vehicleId, int year, String vehicleMake, String vehicleModel){
        this.VehicleMakeAndModel = new VehicleModel(vehicleMake, vehicleModel);
        VehicleId = vehicleId;
        Year = year;
    }
    public Vehicle(int vehicleId, int year, String licensePlate, String vin, String color, boolean isPurchase, int purchasePrice, Date purchaseDate) {
        VehicleId = vehicleId;
        Year = year;
        LicensePlate = licensePlate;
        Vin = vin;
        Color = color;
        IsPurchase = isPurchase;
        PurchasePrice = purchasePrice;
        PurchaseDate = purchaseDate;
        VehicleMakeAndModel = new VehicleModel();
    }
    //endregion

    //region GETTERS/SETTERS

    public int getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(int vehicleId) {
        VehicleId = vehicleId;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getLicensePlate() {
        return LicensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        LicensePlate = licensePlate;
    }

    public String getVin() {
        return Vin;
    }

    public void setVin(String vin) {
        Vin = vin;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public boolean isPurchase() {
        return IsPurchase;
    }

    public void setIsPurchase(boolean isPurchase) {
        IsPurchase = isPurchase;
    }

    public int getPurchasePrice() {
        return PurchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        PurchasePrice = purchasePrice;
    }

    public Date getPurchaseDate() {
        return PurchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        PurchaseDate = purchaseDate;
    }

    public VehicleModel getVehicleMakeAndModel() {
        return VehicleMakeAndModel;
    }

    public void setVehicleMakeAndModel(VehicleModel vehicleMakeAndModel) {
        VehicleMakeAndModel = vehicleMakeAndModel;
    }
    //endregion
    //region CUSTOM METHODS

    public String VehicleToString(){
        String vehicleToString = "Vehicle ID#"
                + VehicleId + " is a "
                + Year + " "
                + VehicleMakeAndModel.getVehicleMakeName()
                + " " + VehicleMakeAndModel.getVehicleModelName() + ".";
        return vehicleToString;
    }
    //endregion


    @Override
    public int compareTo(Object o) {
        Vehicle other = (Vehicle) o;
        if (getVehicleId() > other.getVehicleId())
            return 1;
        else if (getVehicleId() < other.getVehicleId())
            return -1;
        else
            return 0;
    }
}
