package com.astontech.bo;

import java.util.Date;

public class VehicleMake extends BaseBO {
    private int VehicleMakeId;
    private String VehicleMakeName;

    public VehicleMake(){
    }
    public VehicleMake(String vehicleMakeName, String vehicleModelName) {
        VehicleMakeName = vehicleMakeName;
    }

    public int getVehicleMakeId() {
        return VehicleMakeId;
    }

    public void setVehicleMakeId(int vehicleMakeId) {
        VehicleMakeId = vehicleMakeId;
    }

    public String getVehicleMakeName() {
        return VehicleMakeName;
    }

    public void setVehicleMakeName(String vehicleMakeName) {
        VehicleMakeName = vehicleMakeName;
    }


}
