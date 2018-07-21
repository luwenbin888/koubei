package com.MockingJ.koubei.model;

import java.util.Date;
import java.util.List;

public class EntityProperty {
    private int id;
    private int userRoleId;
    private int entityTypeId;
    private String entityPropertyJson;
    private List<Property> kouBeiProperties;
    private Date createdDate;
    private int createdBy;
    private Date lastUpdatedDate;
    private int lastUpdatedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getEntityTypeId() {
        return entityTypeId;
    }

    public void setEntityTypeId(int entityTypeId) {
        this.entityTypeId = entityTypeId;
    }

    public String getEntityPropertyJson() {
        return entityPropertyJson;
    }

    public void setEntityPropertyJson(String jsonPropertyStr) {
        this.entityPropertyJson = jsonPropertyStr;
    }

    public List<Property> getKouBeiProperties() {
        return kouBeiProperties;
    }

    public void setKouBeiProperties(List<Property> kouBeiProperties) {
        this.kouBeiProperties = kouBeiProperties;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public int getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(int lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
