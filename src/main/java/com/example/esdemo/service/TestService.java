package com.example.esdemo.service;

import com.example.esdemo.model.Test;

import java.util.List;

public interface TestService {

    void saveEntity(Test test);

    void saveEntity(List<Test> entityList);

    List<Test> searchEntity(String searchContent);
}
