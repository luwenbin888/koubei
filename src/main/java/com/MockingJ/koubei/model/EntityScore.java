package com.MockingJ.koubei.model;

import java.util.Date;
import java.util.List;

public class EntityScore {
    private int id;
    private int userId;
    private int entityId;
    private String entityScoreJson;
    private List<Score> entityScore;
    private Date createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getEntityScoreJson() {
        return entityScoreJson;
    }

    public void setEntityScoreJson(String entityScoreJson) {
        this.entityScoreJson = entityScoreJson;
    }

    public List<Score> getEntityScore() {
        return entityScore;
    }

    public void setEntityScore(List<Score> entityScore) {
        this.entityScore = entityScore;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createDate) {
        this.createdDate = createDate;
    }
}
