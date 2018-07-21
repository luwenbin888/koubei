package com.MockingJ.koubei.mapper;

import com.MockingJ.koubei.model.EntityScore;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EntityScoreMapper {

    @Insert("INSERT INTO EntityScore(UserId, EntityId, EntityScore) VALUES (#{userId}, #{entityId})")
    @Options(useGeneratedKeys=true, keyProperty="Id")
    int createNewEntityScore(EntityScore entityScore);

    @Results({
            @Result(property = "id", column = "Id"),
            @Result(property = "userId", column = "UserId"),
            @Result(property = "entityId", column = "EntityId"),
            @Result(property = "entityScoreJson", column = "EntityScore"),
            @Result(property = "createdDate", column = "CreatedDate")
    })
    @Select("SELECT Id, UserId, EntityId, EntityScore, CreatedDate FROM EntityScore WHERE UserId=#{userId} AND entityId=#{entityId}")
    List<EntityScore> getEntityScore(@Param("userId") int userId, @Param("entityId")int entityId);
}
