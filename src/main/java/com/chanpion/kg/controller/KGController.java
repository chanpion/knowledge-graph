package com.chanpion.kg.controller;

import com.chanpion.kg.dao.Neo4jDao;
import com.chanpion.kg.model.BaseNode;
import com.chanpion.kg.neo4j.Neo4jUtil;
import org.neo4j.driver.v1.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author April Chen
 * @date 2020/1/14 15:21
 */
@RestController
@RequestMapping("/kg")
public class KGController {
    private static Logger logger = LoggerFactory.getLogger(KGController.class);
    @Resource
    private Neo4jDao neo4jDao;

    @RequestMapping("/entity/name/{name}")
    public Mono<BaseNode> findEntityByName(@PathVariable String name) {
        Record record = neo4jDao.findNodeByName(name);
        logger.info("entity:{}", record);
        return Mono.just(Neo4jUtil.convert(record));
    }
}
