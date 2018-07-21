package com.MockingJ.koubei;

import com.MockingJ.koubei.model.EntityProperty;
import com.MockingJ.koubei.model.EntityScore;
import com.MockingJ.koubei.model.Property;
import com.MockingJ.koubei.model.Score;
import com.google.gson.Gson;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelTests {

    @Test
    @Ignore
    public void testGeneratePropertyModel() {
        EntityProperty entityProperty = new EntityProperty();

        entityProperty.setId(1);
        entityProperty.setUserRoleId(1);
        entityProperty.setEntityTypeId(1);
        entityProperty.setCreatedDate(new Date());
        entityProperty.setCreatedBy(2);
        entityProperty.setLastUpdatedDate(new Date());
        entityProperty.setLastUpdatedBy(3);

        List<Property> properties = new ArrayList<>();
        Property p1 = new Property();
        p1.setSeq(1);
        p1.setName("Property1");

        Property p2 = new Property();
        p2.setSeq(2);
        p2.setName("Property2");

        Property p3 = new Property();
        p3.setSeq(3);
        p3.setName("Property3");

        properties.add(p1);
        properties.add(p2);
        properties.add(p3);

        entityProperty.setKouBeiProperties(properties);

        Gson gson = new Gson();
        String entityPropertyJson = gson.toJson(entityProperty);
        System.out.println(entityPropertyJson);
    }

    @Ignore
    @Test
    public void testGenerateScoreModel() {
        EntityScore entityScore = new EntityScore();

        entityScore.setId(1);
        entityScore.setUserId(1);
        entityScore.setEntityId(1);
        entityScore.setCreatedDate(new Date());

        List<Score> scoreList = new ArrayList<>();
        Score score1 = new Score();
        score1.setPropertyName("Property1");
        score1.setScore(10);

        Score score2 = new Score();
        score2.setPropertyName("Property2");
        score2.setScore(9);

        Score score3 = new Score();
        score3.setPropertyName("Property3");
        score3.setScore(8);

        scoreList.add(score1);
        scoreList.add(score2);
        scoreList.add(score3);

        entityScore.setEntityScore(scoreList);

        Gson gson = new Gson();
        String entityScoreJson = gson.toJson(entityScore);
        System.out.println(entityScoreJson);
    }

    @Test
    public void test1() {
        String hostName = "mkjsqldb01.database.windows.net";
        String dbName = "mkj-db_from_chinacloudsites.cn_20180215";
        String user = "mkjdb@mkjsqldb01";
        String password = "ZYdB0201";
        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
        System.out.println(url);
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
            String schema = connection.getSchema();
            System.out.println("Successful connection - Schema: " + schema);

            System.out.println("Query data example:");
            System.out.println("=========================================");

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * FROM Projects";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next())
                {
                    System.out.println(resultSet.getString(1) + " "
                            + resultSet.getString(2));
                }
                connection.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
