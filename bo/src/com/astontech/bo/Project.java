package com.astontech.bo;

import java.util.Date;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class Project extends BaseBO {
    private int ProjectId;
    //ClientId;
    //EntityTypeId
    private int Rate;
    private Date StartDate;
    private Date EndDate;
    private String Projectname;

    public Project(){}

    public Project(int projectId, int rate, Date startDate, Date endDate, String projectname) {
        ProjectId = projectId;
        Rate = rate;
        StartDate = startDate;
        EndDate = endDate;
        Projectname = projectname;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
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

    public String getProjectname() {
        return Projectname;
    }

    public void setProjectname(String projectname) {
        Projectname = projectname;
    }
}
