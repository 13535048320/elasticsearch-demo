//package com.example.esdemo.service.impl;
//
//import com.example.esdemo.model.Test;
//import com.example.esdemo.service.TestService;
//import io.searchbox.client.JestClient;
//import io.searchbox.client.JestResult;
//import io.searchbox.core.Bulk;
//import io.searchbox.core.Index;
//import io.searchbox.core.Search;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//
//@Service
//public class TestServiceImpl implements TestService {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);
//
//    @Autowired
//    private JestClient jestClient;
//
//    @Override
//    public void saveEntity(Test test) {
//        Index index = new Index.Builder(test).index(Test.INDEX_NAME).type(Test.TYPE).build();
//        try {
//            jestClient.execute(index);
//            LOGGER.info("ES 插入完成");
//        } catch (IOException e) {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//
//    /**
//     * 批量保存内容到ES
//     */
//    @Override
//    public void saveEntity(List<Test> testList) {
//        Bulk.Builder bulk = new Bulk.Builder();
//        for(Test test : testList) {
//            Index index = new Index.Builder(test).index(Test.INDEX_NAME).type(Test.TYPE).build();
//            bulk.addAction(index);
//        }
//        try {
//            jestClient.execute(bulk.build());
//            LOGGER.info("ES 插入完成");
//        } catch (IOException e) {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    /**
//     * 在ES中搜索内容
//     */
//    @Override
//    public List<Test> searchEntity(String searchContent){
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        //searchSourceBuilder.query(QueryBuilders.queryStringQuery(searchContent));
//        //searchSourceBuilder.field("name");
//        searchSourceBuilder.query(QueryBuilders.matchQuery("name",searchContent));
//        Search search = new Search.Builder(searchSourceBuilder.toString())
//                .addIndex(Test.INDEX_NAME).addType(Test.TYPE).build();
//        try {
//            JestResult result = jestClient.execute(search);
//            return result.getSourceAsObjectList(Test.class);
//        } catch (IOException e) {
//            LOGGER.error(e.getMessage());
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
