package com.ei.eisoccermanagment.soccer.model;

import com.ei.eisoccermanagment.shared.Validators;

public class Review  {
    private int reviewId;
    private String first_name;
    private String last_name;
    private String description;

    public Review() {
    }

    public Review(int reviewId, String first_name, String last_name, String description) {
        this.reviewId = reviewId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.description = description;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
