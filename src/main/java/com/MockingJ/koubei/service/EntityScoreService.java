package com.MockingJ.koubei.service;

import com.MockingJ.koubei.mapper.EntityScoreMapper;
import com.MockingJ.koubei.model.EntityScore;
import com.MockingJ.koubei.model.Score;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityScoreService {

    @Autowired
    private EntityScoreMapper entityScoreMapper;

    public List<EntityScore> getEntityScore(int userId, int entityId) {
        return entityScoreMapper.getEntityScore(userId, entityId);
    }

    public int addNewEntityScore(int userId, int entityId, List<Score> scoreList) {
        EntityScore entityScore = new EntityScore();
        entityScore.setUserId(userId);
        entityScore.setEntityId(entityId);
        entityScore.setEntityScore(scoreList);

        Gson gson = new Gson();
        String entityScoreJson = gson.toJson(scoreList);
        entityScore.setEntityScoreJson(entityScoreJson);
        return entityScoreMapper.createNewEntityScore(entityScore);
    }
}
