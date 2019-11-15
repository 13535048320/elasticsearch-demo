package com.example.esdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
@Document(indexName = "es-demo", type = "test", indexStoreType = "fs", shards = 5, replicas = 1, refreshInterval = "-1")
public class Test implements Serializable {

    private static final long serialVersionUID = -763638353551774166L;

    @Id
    private String id;

    private String name;
}
