package com.chanpion.kg.neo4j;

import com.chanpion.kg.model.BaseNode;
import org.neo4j.driver.internal.InternalNode;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author April Chen
 * @date 2020/1/14 17:17
 */
public class Neo4jUtil {

    public static BaseNode convert(Record record) {
        BaseNode baseNode = new BaseNode();
        Value value = record.get(0);
        InternalNode node = (InternalNode) value.asNode();
        baseNode.setId(node.id());
        baseNode.setLabels(new ArrayList<>(node.labels()));
        baseNode.setProperties(node.asMap());
        baseNode.setName(node.get("name").asString());
        return baseNode;
    }
}
