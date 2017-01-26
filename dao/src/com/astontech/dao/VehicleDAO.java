package com.astontech.dao;

import com.astontech.bo.Vehicle;
import com.astontech.bo.VehicleMake;
import com.astontech.bo.VehicleModel;

import java.util.List;

/**
 * Created by kylebutz1 on 11/10/2016.
 */
public interface VehicleDAO {
    public Vehicle getVehicleById(int vehicleId);
    public List<Vehicle> getVehicleList();
    // Fetches Make & Model
    public List<VehicleMake> getVehicleMakeList();
    public List<VehicleModel> getVehicleModelListByMakeId(int vehicleMakeId);
    public List<VehicleModel> getVehicleModelList();


    //notes:    EXECUTE METHODS
    public int insertVehicle(Vehicle vehicle);
    public boolean updateVehicle(Vehicle vehicle);
    public boolean deleteVehicle(int vehicleId);

}
