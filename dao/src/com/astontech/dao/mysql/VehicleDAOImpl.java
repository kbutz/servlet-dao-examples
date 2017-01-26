package com.astontech.dao.mysql;

import com.astontech.bo.Vehicle;
import com.astontech.bo.VehicleMake;
import com.astontech.bo.VehicleModel;
import com.astontech.dao.VehicleDAO;
import common.helpers.DateHelper;

import javax.xml.transform.Result;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl extends MySQL implements VehicleDAO {

    @Override
    public Vehicle getVehicleById(int vehicleId) {
        Connect();
        Vehicle vehicle = null; //not instantiated because if no records returned, we don't want to use memory
        try {
            String sp = "{call GetVehicle(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,GET_BY_ID);
            cStmt.setInt(2,vehicleId);
            ResultSet rs = cStmt.executeQuery();

            if (rs.next()){
                vehicle = HydrateObject(rs);
            }
            cStmt.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return vehicle;
    }

    @Override
    public List<Vehicle> getVehicleList() {
        Connect();
        List<Vehicle> vehicleList = new ArrayList<>();
        try{
            String sp = "{call GetVehicle(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,GET_COLLECTION);
            cStmt.setInt(2, 0);

            ResultSet rs = cStmt.executeQuery();

            while (rs.next()){
                vehicleList.add(HydrateObject(rs));
            }
            cStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleList;
    }

    @Override
    public List<VehicleMake> getVehicleMakeList() {
        Connect();
        List<VehicleMake> vehicleMakeList = new ArrayList<>();
        try {
            String sp = "{call GetMake(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,GET_COLLECTION);
            cStmt.setInt(2,0);
            ResultSet rs = cStmt.executeQuery();

            while (rs.next()){
                vehicleMakeList.add(HydrateObjectForMake(rs));
            }
            cStmt.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return vehicleMakeList;
    }

    @Override
    public List<VehicleModel> getVehicleModelList() {
        Connect();
        List<VehicleModel> vehicleModelList = new ArrayList<>();
        try {
            String sp = "{call GetModel(?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,GET_COLLECTION);
            cStmt.setInt(2,0);
            cStmt.setInt(3,0);
            ResultSet rs = cStmt.executeQuery();

            while (rs.next()){
                vehicleModelList.add(HydrateObjectForModel(rs));
            }
            cStmt.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return vehicleModelList;
    }

    @Override
    public List<VehicleModel> getVehicleModelListByMakeId(int vehicleMakeId) {
        Connect();
        List<VehicleModel> vehicleModelList = new ArrayList<>();
        try {
            String sp = "{call GetModel(?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,21);
            cStmt.setInt(2,0);
            cStmt.setInt(3,vehicleMakeId);
            ResultSet rs = cStmt.executeQuery();

            while (rs.next()){
                vehicleModelList.add(HydrateObjectForModel(rs));
            }
            cStmt.close();
        } catch (SQLException e) {
            logger.error(e);
        }

        return vehicleModelList;
    }

    @Override
    public int insertVehicle(Vehicle vehicle) {
        Connect();
        int id = 0;
        //  call ExecEmployee(30,7,'2016-10-01','2017-11-01')
        try {
            String sp = "{call ExecVehicle(?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, INSERT);
            cStmt.setInt(2, 0);
            cStmt.setInt(3, vehicle.getYear());
            cStmt.setString(4, vehicle.getLicensePlate());
            cStmt.setString(5, vehicle.getVin());
            cStmt.setString(6, vehicle.getColor());
            cStmt.setInt(7, vehicle.getVehicleMakeAndModel().getVehicleModelId());

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
            cStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
        // todo: disconnect()
    }

    @Override
    public boolean updateVehicle(Vehicle vehicle) {
        Connect();
        int id = 0;
        //  call ExecEmployee(30,7,'2016-10-01','2017-11-01')
        try {
            String sp = "{call ExecVehicle(?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, UPDATE);
            cStmt.setInt(2, vehicle.getVehicleId());
            cStmt.setInt(3, vehicle.getYear());
            cStmt.setString(4, vehicle.getLicensePlate());
            cStmt.setString(5, vehicle.getVin());
            cStmt.setString(6, vehicle.getColor());
            cStmt.setInt(7, vehicle.getVehicleMakeAndModel().getVehicleModelId());

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
            cStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id > 0;
    }

    @Override
    public boolean deleteVehicle(int vehicleId) {
        Connect();
        int id = 0;
        //  call ExecEmployee(30,7,'2016-10-01','2017-11-01')
        try {
            String sp = "{call ExecVehicle(?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, vehicleId);
            cStmt.setInt(3, 0);
            cStmt.setString(4, "");
            cStmt.setString(5, "");
            cStmt.setString(6, "");
            cStmt.setInt(7, 0);

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
            cStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id > 0;
    }

    // Hydrate Object
    private static Vehicle HydrateObject(ResultSet rs) throws SQLException {
        Vehicle vehicle = new Vehicle();
        //Hydrate Person object with resultset
        vehicle.setVehicleId(rs.getInt(1));
        vehicle.setYear(rs.getInt(2));
        vehicle.setLicensePlate(rs.getString(3));
        vehicle.setVin(rs.getString(4));
        vehicle.setColor(rs.getString(5));
        vehicle.getVehicleMakeAndModel().setVehicleModelId(rs.getInt(6));
        vehicle.getVehicleMakeAndModel().setVehicleMakeId(rs.getInt(7));

        return vehicle;
    }

    // Hydrate Make Object
    private static VehicleMake HydrateObjectForMake(ResultSet rs) throws SQLException {
        VehicleMake vehicleMake = new VehicleMake();
        //Hydrate VehicleMake object with resultset
        vehicleMake.setVehicleMakeId(rs.getInt(1));
        vehicleMake.setVehicleMakeName(rs.getString(2));
        return vehicleMake;
    }

    // Hydrate Model Object
    private static VehicleModel HydrateObjectForModel(ResultSet rs) throws SQLException {
        VehicleModel vehicleModel = new VehicleModel();
        //Hydrate VehicleModel object with resultset
        vehicleModel.setVehicleModelId(rs.getInt(1));
        vehicleModel.setVehicleModelName(rs.getString(3));
        return vehicleModel;
    }
}
