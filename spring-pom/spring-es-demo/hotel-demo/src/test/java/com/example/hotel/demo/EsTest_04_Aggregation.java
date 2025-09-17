package com.example.hotel.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class EsTest_04_Aggregation {
    private RestHighLevelClient client;
    private ObjectMapper objectMapper;
    
    @Test
    public void testAgg() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        // 只要聚合结果
        request.source().size(0);
        request.source().aggregation(
                AggregationBuilders
                .terms("brandAgg")
                .field("brand")
                .size(20)
        );

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 数据解析
        System.out.println(response);
        Aggregations aggregations = response.getAggregations();
        Terms brandAgg = aggregations.get("brandAgg");
        List<? extends Terms.Bucket> buckets = brandAgg.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            String brandName = bucket.getKeyAsString();
            System.out.println(brandName);
        }
        System.out.println(brandAgg);

    }

    @BeforeEach
    public void setUp() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.1.110:9200")
        ));
        this.objectMapper = new ObjectMapper();

    }

    @AfterEach
    public void tearDown() throws IOException {
        this.client.close();
    }
}
