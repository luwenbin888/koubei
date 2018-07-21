package com.MockingJ.koubei.controller;

import com.MockingJ.koubei.model.EntityScore;
import com.MockingJ.koubei.model.Score;
import com.MockingJ.koubei.service.EntityScoreService;
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
@RequestMapping("/mockingJ/koubei/api/1.0/user/{userId}/entity/{entityId}")
@Api(description = "CRUD on the Koubei score for an entity (i.e. 物业，业委会，供应商), give a logon users")
public class ScoreController {
    private static Logger logger = LoggerFactory.getLogger(PropertyController.class);

    @Autowired
    private EntityScoreService entityScoreService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    @ApiOperation("Get the user score on the entity")
    public List<EntityScore> get(@PathVariable int userId, @PathVariable  int entityId, HttpServletResponse httpResponse) {

        if (userId <=0 || entityId <= 0) {
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }

        logger.info("Received request to get entity score userId {}, entityId {}", userId, entityId);

        List<EntityScore> result = null;
        try {
            result = entityScoreService.getEntityScore(userId, entityId);
            httpResponse.setStatus(HttpStatus.OK.value());
        }
        catch (Exception ex) {
            logger.error("Error in get entity score userId {}, entityId {}", userId, entityId);
            httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return result;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @ApiOperation("User creates the score on the entity")
    public void create(@PathVariable int userId, @PathVariable int entityId, @RequestBody List<Score> scoreList, HttpServletResponse httpResponse) {

        if (userId <=0 || entityId <= 0 || scoreList == null || scoreList.size() == 0) {
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        logger.info("Received request to create entity score userId {}, entityId {}", userId, entityId);

        try {
            entityScoreService.addNewEntityScore(userId, entityId, scoreList);
            httpResponse.setStatus(HttpStatus.OK.value());
        }
        catch (Exception ex) {
            logger.error("Error in get entity score userId {}, entityId {}", userId, entityId);
            httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
