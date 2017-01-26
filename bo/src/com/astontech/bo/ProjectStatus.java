package com.astontech.bo;

import java.util.Date;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class ProjectStatus extends BaseBO {
    private int ProjectStatusId;
    //ProjectId;
    //EntityTypeId
    private String Notes;
    private int PercentComplete;
    private Date StartDate;
    private Date EndDate;

    public ProjectStatus(){}

    public ProjectStatus(int projectStatusId, String notes, int percentComplete, Date startDate, Date endDate) {
        ProjectStatusId = projectStatusId;
        Notes = notes;
        PercentComplete = percentComplete;
        StartDate = startDate;
        EndDate = endDate;
    }

    public int getProjectStatusId() {
        return ProjectStatusId;
    }

    public void setProjectStatusId(int projectStatusId) {
        ProjectStatusId = projectStatusId;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public int getPercentComplete() {
        return PercentComplete;
    }

    public void setPercentComplete(int percentComplete) {
        PercentComplete = percentComplete;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }
}
