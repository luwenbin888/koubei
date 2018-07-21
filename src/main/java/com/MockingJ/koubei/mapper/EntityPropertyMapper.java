package com.MockingJ.koubei.mapper;

import com.MockingJ.koubei.model.EntityProperty;
import org.apache.ibatis.annotations.*;

import java.util.Date;

@Mapper
public interface EntityPropertyMapper {

    @Insert("INSERT INTO EntityScoreProperty(UserRoleId, EntityTypeId, Properties, CreatedBy, LastUpdatedBy) VALUES (#{userRoleId}, #{entityTypeId}, #{propertyJson}, #{adminId}, #{adminId})")
    int createNewEntityProperty(@Param("adminId") int adminId, @Param("userRoleId") int userRoleId, @Param("entityTypeId") int entityTypeId, @Param("propertyJson") String propertyJson);

    @Update("UPDATE EntityScoreProperty SET Properties=#{entityPropertyJson}, LastUpdatedDate=#{lastUpdatedDate}, LastUpdatedBy=#{adminId} WHERE UserRoleId=#{userRoleId} AND entityTypeId=#{entityTypeId}")
    int updateEntityProperty(int adminId, int userRoleId, int entityTypeId, String propertyJson, Date lastUpdateDate);

    @Results({
            @Result(property = "id", column = "Id"),
            @Result(property = "userRoleId", column = "UserRoleId"),
            @Result(property = "entityTypeId", column = "EntityTypeId"),
            @Result(property = "entityPropertyJson", column = "Properties"),
            @Result(property = "createdDate", column = "CreatedDate"),
            @Result(property = "createdBy", column = "CreatedBy"),
            @Result(property = "lastUpdatedDate", column = "LastUpdatedDate"),
            @Result(property = "lastUpdatedBy", column = "LastUpdatedBy")
    })
    @Select("SELECT Id, UserRoleId, EntityTypeId, Properties, CreatedDate, CreatedBy, LastUpdatedDate, LastUpdatedBy FROM EntityScoreProperty WHERE UserRoleId=#{userRoleId} AND entityTypeId=#{entityTypeId}")
    EntityProperty getEntityProperty(@Param("userRoleId") int userRoleId, @Param("entityTypeId")int entityTypeId);

    @Delete("DELETE FROM EntityScoreProperty WHERE UserRoleId=#{userRoleId} AND entityTypeId=#{entityTypeId}")
    int deleteEntityProperty(@Param("userRoleId") int userRoleId, @Param("entityTypeId")int entityTypeId);
}
