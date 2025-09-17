package com.example.hotel.demo;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.example.hotel.demo.constants.HotelConstants.MAPPING_TEMPLATE;

public class EsTest_01_Index {

    private RestHighLevelClient client;

    private final String IndexName = "hotel";

    private final String host = "http://localhost:9200"; // es 服务器的地址

    @Test
    public void testInit() {
        System.out.println(client);
    }

    /**
     * 创建 hotel 索引库
     * 
     * @throws IOException
     */
    @Test
    public void createHotelIndex() throws IOException {
        // 1. 创建request对象
        CreateIndexRequest request = new CreateIndexRequest(IndexName);
        // 2. 准备请求参数：DSL语句
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        // 3. 发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    /**
     * 删除 hotel 索引库
     * 
     * @throws IOException
     */
    @Test
    public void deleteHotelIndex() throws IOException {
        // 1. 创建request对象
        DeleteIndexRequest request = new DeleteIndexRequest(IndexName);
        // 2. 发送请求
        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    /**
     * 判断 hotel 索引库是否存在
     * 
     * @throws IOException
     */
    @Test
    public void existsHotelIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest(IndexName);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);

    }

    @BeforeEach
    public void setUp() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create(host)));

    }

    @AfterEach
    public void tearDown() throws IOException {
        this.client.close();
    }
}
