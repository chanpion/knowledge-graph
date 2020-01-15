package com.chanpion.kg.model;

import java.util.List;
import java.util.Map;

/**
 * 图谱节点
 *
 * @author April Chen
 * @date 2020/1/14 17:13
 */
public class BaseNode {
    private Long id;
    private String name;
    private List<String> labels;
    private Map<String, Object> properties;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
