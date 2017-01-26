package com.astontech.bo;

import java.util.Date;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class VehicleStatus extends BaseBO {
    private int VehicleStatusId;
    //VehicleId;
    //EntityTypeId;
    private String Notes;
    private Date CreateDate;

    public VehicleStatus(){}
    public VehicleStatus(int vehicleStatusId, String notes, Date createDate) {
        VehicleStatusId = vehicleStatusId;
        Notes = notes;
        CreateDate = createDate;
    }

    public int getVehicleStatusId() {
        return VehicleStatusId;
    }

    public void setVehicleStatusId(int vehicleStatusId) {
        VehicleStatusId = vehicleStatusId;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }
}
