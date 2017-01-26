package com.astontech.bo;

import java.util.Date;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class TrainingData extends BaseBO {
    private int TrainingDateId;
    //TrainingId;
    //EntityTypeId;
    private String TrainingDateValue;
    private Date CreateDate;

    public TrainingData(int trainingDateId, String trainingDateValue, Date createDate) {
        TrainingDateId = trainingDateId;
        TrainingDateValue = trainingDateValue;
        CreateDate = createDate;
    }

    public int getTrainingDateId() {
        return TrainingDateId;
    }

    public void setTrainingDateId(int trainingDateId) {
        TrainingDateId = trainingDateId;
    }

    public String getTrainingDateValue() {
        return TrainingDateValue;
    }

    public void setTrainingDateValue(String trainingDateValue) {
        TrainingDateValue = trainingDateValue;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }
}
