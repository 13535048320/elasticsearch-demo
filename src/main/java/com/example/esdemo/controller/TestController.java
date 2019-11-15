package com.example.esdemo.controller;

import com.example.esdemo.model.Test;
import com.example.esdemo.service.impl.RequestRepository;
import com.example.esdemo.util.ResultMessage;
import com.example.esdemo.util.UUIDUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RequestRepository requestRepository;

    @PostMapping
    public ResultMessage save(@RequestBody Test test) {
        test.setId(UUIDUtil.generateID());
        return ResultMessage.ok(requestRepository.save(test));
    }

    @GetMapping
    public ResultMessage get() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("id"));
        Iterable<Test> tests = requestRepository.findAll(pageable);
        return tests.iterator().hasNext() ? ResultMessage.ok(tests) : ResultMessage.build(200, "test is null!");
    }

    @GetMapping("/{id}")
    public ResultMessage getById(@PathVariable("id") String id, @RequestParam(value = "name", required = false) String name) {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("id"));

        // BoolQueryBuilder 复合查询, 多个must相当于and, 多个should相当于or
        BoolQueryBuilder builder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("id", id));
        Iterable<Test> tests = requestRepository.search(builder, pageable);
        return tests.iterator().hasNext() ? ResultMessage.ok(tests) : ResultMessage.build(200, "test is null!");
    }

    @GetMapping("/getByName")
    public ResultMessage getByName(@RequestParam(value = "name") String name) {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("id"));

        // wildcardQuery 模糊查询
        QueryBuilder builder = QueryBuilders.wildcardQuery("name.keyword", "*" + name + "*");

        // multiMatchQuery 相当于in (String...)
//        QueryBuilder builder = QueryBuilders.multiMatchQuery("name.keyword",
//                "北京", "南京");

        Iterable<Test> tests = requestRepository.search(builder, pageable);
        return tests.iterator().hasNext() ? ResultMessage.ok(tests) : ResultMessage.build(200, "test is null!");
    }
}
