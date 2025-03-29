package com.ei.eisoccermanagment.soccer.model;

import java.time.Instant;
import java.util.Date;

public class Practice {
    private int practiceId;
    private int coachId;
    private int userId;
    private String location;
    private Instant practiceDate;


    public Practice() {
    }


    public Practice(int practiceId, int coachId, int userId, String location, Instant practiceDate) {
        this.practiceId = practiceId;
        this.coachId = coachId;
        this.userId = userId;
        this.location = location;
        this.practiceDate = practiceDate;
    }

    public int getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(int practiceId) {
        this.practiceId = practiceId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Instant getPracticeDate() {
        return practiceDate;
    }

    public void setPracticeDate(Instant practiceDate) {
        this.practiceDate = practiceDate;
    }

    @Override
    public String toString() {
        return "Practice{" +
                "practiceId=" + practiceId +
                ", coachId=" + coachId +
                ", userId=" + userId +
                ", location='" + location + '\'' +
                ", practiceDate=" + practiceDate +
                '}';
    }
}
