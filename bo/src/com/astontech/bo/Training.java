package com.astontech.bo;

import java.util.Date;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class Training extends BaseBO {
    private int TrainingId;
    //EmployeeId;
    private String TrainingName;
    private Date CreateDate;

    public Training(){}

    public Training(int trainingId, String trainingName, Date createDate) {
        TrainingId = trainingId;
        TrainingName = trainingName;
        CreateDate = createDate;
    }

    public int getTrainingId() {
        return TrainingId;
    }

    public void setTrainingId(int trainingId) {
        TrainingId = trainingId;
    }

    public String getTrainingName() {
        return TrainingName;
    }

    public void setTrainingName(String trainingName) {
        TrainingName = trainingName;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }
}
