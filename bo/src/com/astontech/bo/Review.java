package com.astontech.bo;

import java.util.Date;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class Review extends BaseBO {
    private int ReviewId;
    private String ReviewName;
    private Date ReviewDate;
    //EmployeeId

    public Review(){}

    public Review(int reviewId, String reviewName, Date reviewDate) {
        ReviewId = reviewId;
        ReviewName = reviewName;
        ReviewDate = reviewDate;
    }

    public int getReviewId() {
        return ReviewId;
    }

    public void setReviewId(int reviewId) {
        ReviewId = reviewId;
    }

    public String getReviewName() {
        return ReviewName;
    }

    public void setReviewName(String reviewName) {
        ReviewName = reviewName;
    }

    public Date getReviewDate() {
        return ReviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        ReviewDate = reviewDate;
    }
}
