package com.chanpion.kg.dao;

import org.neo4j.driver.v1.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author April Chen
 * @date 2020/1/14 11:13
 */
@Repository
public class Neo4jDao {

    @Resource
    private Driver driver;

    public Record findNodeByName(String name) {
        try (Session session = driver.session()) {
            String sql = String.format("MATCH (n) WHERE n.name='%s' return n", name);
            StatementResult statementResult = session.readTransaction(tx -> tx.run(sql));
            if (statementResult.hasNext()) {
                return statementResult.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
