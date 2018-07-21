package com.MockingJ.koubei.controller;

import com.MockingJ.koubei.exception.DuplicateEntityPropertyException;
import com.MockingJ.koubei.exception.EntityPropertyNotExistException;
import com.MockingJ.koubei.exception.InvalidPropertyException;
import com.MockingJ.koubei.model.Property;
import com.MockingJ.koubei.service.EntityPropertyService;
import com.MockingJ.koubei.model.EntityProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/mockingJ/koubei/api/1.0/role/{userRoleId}/entityType/{entityTypeId}")
@Api(description = "CRUD on the Koubei property, for admin only, maintain UserRole, EntityType allowed score properties")
public class PropertyController {

    @Autowired
    private EntityPropertyService entityPropertyService;
    private static Logger logger = LoggerFactory.getLogger(PropertyController.class);

    /**
     * Gets Koubei property to score for given userRoleId and entityTypeId
     * @param userRoleId
     * @param entityTypeId
     * @param httpResponse
     * @return EntityProperty
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    @ApiOperation("Gets Koubei properties for a UserRoleId and EntityTypeId")
    public List<Property> get(@PathVariable int userRoleId, @PathVariable  int entityTypeId, HttpServletResponse httpResponse) {

        if (userRoleId <=0 || entityTypeId <= 0) {
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }

        logger.info("Received request to get entity property userRoleId {}, entityTypeId {}", userRoleId, entityTypeId);
        // todo: check userRoleId and entityTypeId

        List<Property> entityPropertyList = null;

        try {
            entityPropertyList = entityPropertyService.getEntityProperties(userRoleId, entityTypeId);
            if (entityPropertyList != null) {
                httpResponse.setStatus(HttpStatus.OK.value());
            } else {
                httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            }
        }
        catch (Exception ex) {
            logger.error("Fail to process get entity property userRoleId {}, entityTypeId {}", userRoleId, entityTypeId, ex);
            httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return entityPropertyList;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @ApiOperation("Create Koubei properties for a UserRoleId, EntityTypeId")
    public void create(@RequestHeader int logonUserId, @PathVariable int userRoleId, @PathVariable int entityTypeId, @RequestBody List<Property> propertyList,
                       HttpServletResponse httpResponse) {
        if (userRoleId <=0 || entityTypeId <= 0 || propertyList == null || propertyList.size() == 0) {
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        logger.info("Received request to create entity property userRoleId {}, entityTypeId {}", userRoleId, entityTypeId);

        try {
            int newId = entityPropertyService.addNewEntityProperties(logonUserId, userRoleId, entityTypeId, propertyList);
            if (newId <= 0) {
                throw new Exception("Failed to insert new property");
            }

            httpResponse.setStatus(HttpStatus.CREATED.value());

        }
        catch (DuplicateEntityPropertyException | InvalidPropertyException ex) {
            logger.error(ex.getMessage());
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        catch (Exception ex) {
            logger.error("Fail to process get entity property userRoleId {}, entityTypeId {}", userRoleId, entityTypeId, ex);
            httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    @ApiOperation("Update Koubei properties for a UserRoleId, EntityTypeId")
    public void update(@RequestHeader int logonUserId, @PathVariable int userRoleId, @PathVariable int entityTypeId, @RequestBody List<Property> propertyList,
                       HttpServletResponse httpResponse) {
        if (userRoleId <=0 || entityTypeId <= 0 || propertyList == null || propertyList.size() == 0) {
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        logger.info("Received request to update entity property userRoleId {}, entityTypeId {}", userRoleId, entityTypeId);

        try {
            entityPropertyService.updateEntityProperties(logonUserId, userRoleId, entityTypeId, propertyList);

            httpResponse.setStatus(HttpStatus.OK.value());
        }
        catch (EntityPropertyNotExistException | InvalidPropertyException ex) {
            logger.error(ex.getMessage());
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        catch (Exception ex) {
            logger.error("Fail to process get entity property userRoleId {}, entityTypeId {}", userRoleId, entityTypeId, ex);
            httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @RequestMapping(path="", method = RequestMethod.DELETE)
    @ApiOperation("Delete Koubei properties for a UserRoleId, EntityTypeId")
    public void delete(@PathVariable int userRoleId, @PathVariable int entityTypeId, HttpServletResponse httpResponse) {
        if (userRoleId <=0 || entityTypeId <= 0) {
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        }

        logger.info("Received request to delete entity property userRoleId {}, entityTypeId {}", userRoleId, entityTypeId);

        try {
            entityPropertyService.deleteEntityProperties(userRoleId, entityTypeId);

            httpResponse.setStatus(HttpStatus.OK.value());
        }
        catch (EntityPropertyNotExistException ex) {
            logger.error(ex.getMessage());
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        catch (Exception ex) {
            logger.error("Fail to process get entity property userRoleId {}, entityTypeId {}", userRoleId, entityTypeId, ex);
            httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
