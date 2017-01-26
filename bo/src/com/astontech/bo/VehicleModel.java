package com.astontech.bo;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class VehicleModel extends VehicleMake {
    private int VehicleModelId;
    private String VehicleModelName;
    // VehicleMakeId

    public VehicleModel(){}
    public VehicleModel(String vehicleMakeName, String vehicleModelName) {
        VehicleModelName = vehicleModelName;
        this.setVehicleMakeName(vehicleMakeName);
    }

    public int getVehicleModelId() {
        return VehicleModelId;
    }

    public void setVehicleModelId(int vehicleModelId) {
        VehicleModelId = vehicleModelId;
    }

    public String getVehicleModelName() {
        return VehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        VehicleModelName = vehicleModelName;
    }
}
