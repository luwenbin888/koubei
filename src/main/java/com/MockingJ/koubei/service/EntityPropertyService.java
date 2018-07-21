package com.MockingJ.koubei.service;

import com.MockingJ.koubei.exception.DuplicateEntityPropertyException;
import com.MockingJ.koubei.exception.EntityPropertyNotExistException;
import com.MockingJ.koubei.exception.InvalidPropertyException;
import com.MockingJ.koubei.mapper.EntityPropertyMapper;
import com.MockingJ.koubei.model.EntityProperty;
import com.MockingJ.koubei.model.Property;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EntityPropertyService {

    @Autowired
    private EntityPropertyMapper entityPropertyMapper;

    public List<Property> getEntityProperties(int userRoleId, int entityTypeId) {
        List<Property> propertyList = null;
        EntityProperty entityProperty = entityPropertyMapper.getEntityProperty(userRoleId, entityTypeId);
        if (entityProperty == null) {
            return propertyList;
        }

        String entityPropertyJson = entityProperty.getEntityPropertyJson();
        Gson gson = new Gson();
        propertyList = (List<Property>)gson.fromJson(entityPropertyJson, List.class);

        return propertyList;
    }

    public int addNewEntityProperties(int adminId, int userRoleId, int entityTypeId, List<Property> propertyList) throws Exception {
        EntityProperty entityProperty = entityPropertyMapper.getEntityProperty(userRoleId, entityTypeId);
        if (entityProperty != null) {
            String error = "Property for userRoleId %s, entityTypeId %entityTypeId already exists";
            throw new DuplicateEntityPropertyException(String.format(error, userRoleId, entityTypeId));
        }

        Gson gson = new Gson();
        String entityPropertyJson = gson.toJson(propertyList);
        if (StringUtils.isBlank(entityPropertyJson)) {
            throw new InvalidPropertyException("Invalid propertyList");
        }

        return entityPropertyMapper.createNewEntityProperty(adminId, userRoleId, entityTypeId, entityPropertyJson);
    }

    public int updateEntityProperties(int adminId, int userRoleId, int entityTypeId, List<Property> propertyList) throws Exception {
        EntityProperty entityProperty = entityPropertyMapper.getEntityProperty(userRoleId, entityTypeId);
        if (entityProperty == null) {
            String error = "Property for userRoleId %s, entityTypeId %entityTypeId not exist";
            throw new EntityPropertyNotExistException(String.format(error, userRoleId, entityTypeId));
        }

        Gson gson = new Gson();
        String entityPropertyJson = gson.toJson(propertyList);
        if (StringUtils.isBlank(entityPropertyJson)) {
            throw new InvalidPropertyException("Invalid propertyList");
        }

        return entityPropertyMapper.updateEntityProperty(adminId, userRoleId, entityTypeId, entityPropertyJson, new Date());
    }

    public int deleteEntityProperties(int userRoleId, int entityTypeId) throws Exception {

        EntityProperty entityProperty = entityPropertyMapper.getEntityProperty(userRoleId, entityTypeId);
        if (entityProperty == null) {
            String error = "Property for userRoleId %s, entityTypeId %entityTypeId not exist";
            throw new EntityPropertyNotExistException(String.format(error, userRoleId, entityTypeId));
        }

        return entityPropertyMapper.deleteEntityProperty(userRoleId, entityTypeId);
    }
}
