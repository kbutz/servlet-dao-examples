package com.astontech.bo;

import java.util.Date;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class ReviewData extends BaseBO {
    private int ReviewDataId;
    //ReviewId;
    //EntityTypeId;
    private String ReviewDataValue;
    private Date CreateDate;

    public ReviewData(int reviewDataId, String reviewDataValue, Date createDate) {
        ReviewDataId = reviewDataId;
        ReviewDataValue = reviewDataValue;
        CreateDate = createDate;
    }

    public int getReviewDataId() {
        return ReviewDataId;
    }

    public void setReviewDataId(int reviewDataId) {
        ReviewDataId = reviewDataId;
    }

    public String getReviewDataValue() {
        return ReviewDataValue;
    }

    public void setReviewDataValue(String reviewDataValue) {
        ReviewDataValue = reviewDataValue;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }
}
