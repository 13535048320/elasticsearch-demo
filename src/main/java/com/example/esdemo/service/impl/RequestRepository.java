package com.example.esdemo.service.impl;

import com.example.esdemo.model.Test;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RequestRepository
        extends ElasticsearchRepository<Test, Long> {
}
