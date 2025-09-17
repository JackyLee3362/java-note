package com.example.hotel.demo;

import com.example.hotel.demo.entity.HotelDoc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;

/**
 * DSL: ElasticSearch 提供基于 Json 的 DSL(Domain Specific Language) 来定义查询
 * 包括【查询所有（match_all）】，【全文检索（full_text）】，精确查询
 * 全文检索又包括 match_query 和 multi_match_query
 */
@SpringBootTest
public class EsTest_03_Search {
    private RestHighLevelClient client;
    private ObjectMapper objectMapper;
    private final String host = "http://localhost:9200";
    private final String indexName = "hotel";

    /**
     * 查询 index 下的所有文档，并输出记录数
     * match_all
     * 
     * @throws IOException
     */
    @Test
    public void testMatchAll() throws IOException {
        // 1 准备request
        SearchRequest request = new SearchRequest(indexName);
        // 2 组织DSL参数
        request.source().query(QueryBuilders.matchAllQuery());
        // 3 发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4 解析结果
        SearchHits searchHits = response.getHits();
        // 4.1 查询结果条数
        long total = searchHits.getTotalHits().value;
        System.out.println("总共的记录数为" + total);
        // 4.2 查询的结果数组
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            HotelDoc hotelDoc = objectMapper.readValue(json, HotelDoc.class);
            System.out.println(hotelDoc);
        }
    }

    /**
     * 全文检索（full text）的【单字段查询】和【多字段查询】
     * 
     * @throws IOException
     */
    @Test
    public void testMatchOptional() throws IOException {
        // 1 准备 request
        SearchRequest request = new SearchRequest();

        // 2 组织 DSL 参数
        request.source().query(QueryBuilders.matchQuery("all", "如家")); // 单字段查询
        // QueryBuilders.multiMatchQuery("如家","name","business"); // 多字段查询

        // 3 发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        // 4 解析结果
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        System.out.printf("总共的记录数为%d \n", total);
        SearchHit[] hits = searchHits.getHits();
        // 4.1 遍历结果并输出
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            HotelDoc hotelDoc = objectMapper.readValue(json, HotelDoc.class);
            System.out.println(hotelDoc);
        }
    }

    /**
     * 精确查询
     */
    public void testTermQuery() {
        // 词条查询
        QueryBuilders.termQuery("city", "上海");
        // 范围查询
        QueryBuilders.rangeQuery("price").gte(100).lte(150);
    }

    /**
     * 复合查询：布尔查询
     */
    public void testCompoundQuery() {
        // 创建布尔查询
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        // 添加 must 条件
        boolQuery.must(QueryBuilders.termQuery("city", "杭州"));

        // 添加 filter 条件
        boolQuery.filter(QueryBuilders.rangeQuery("price").lte(250));
    }

    /**
     * 排序和分页
     */
    @Test
    public void testPageAndSort() {
        // 准备request
        SearchRequest request = new SearchRequest(indexName);
        // 分页
        request.source().from(0).size(5);
        // 排序
        request.source().sort("price", SortOrder.ASC);

    }

    /**
     * 根据 GeoPoint 对象，构造排序器，然后发送请求
     * 
     * @throws IOException
     */
    @Test
    public void testGeoDistance() throws IOException {
        // 1. 准备 request
        SearchRequest request = new SearchRequest(indexName);
        // 2. 准备DSL
        // 2.1 根据地理坐标排序
        // 构造 GeoPoint 对象
        GeoPoint geoPoint = new GeoPoint("31.21, 121.5");
        // 构造 Geo 排序器
        GeoDistanceSortBuilder geoLocation = SortBuilders
                .geoDistanceSort("location", geoPoint)
                .order(SortOrder.ASC)
                .unit(DistanceUnit.KILOMETERS);
        request.source().sort(geoLocation);
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析响应
        // ...
        System.out.println(response);
    }

    /**
     * Highlight 查询，得到 searchHits 结果，查看总共的 total 数量
     * 如果 hits
     * 
     * @throws IOException
     */
    @Test
    public void testHighlight() throws IOException {
        // 1 构造 request 请求
        SearchRequest request = new SearchRequest(indexName);
        // 2 构造 query: all 字段的值为【如家】
        request.source().query(QueryBuilders.matchQuery("all", "如家"));
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .field("name")
                .requireFieldMatch(false); // 是否与查询字段匹配
        request.source().highlighter(highlightBuilder);
        // 3 发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4 解析结果
        // 4.1 解析 source 结果
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        System.out.printf("总共的记录数为%d \n", total);
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            // 反序列化
            HotelDoc hotelDoc = objectMapper.readValue(json, HotelDoc.class);
            // 获取高亮结果
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (!CollectionUtils.isEmpty(highlightFields)) {
                HighlightField highlightField = highlightFields.get("name");
                if (highlightField != null) {
                    String name = highlightField.getFragments()[0].string();
                    hotelDoc.setName(name);
                    System.out.println(hotelDoc);
                }
            }
        }
        // 4.2 解析highlight 结果

    }

    @BeforeEach
    public void setUp() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create(host)));
        this.objectMapper = new ObjectMapper();

    }

    @AfterEach
    public void tearDown() throws IOException {
        this.client.close();
    }
}
