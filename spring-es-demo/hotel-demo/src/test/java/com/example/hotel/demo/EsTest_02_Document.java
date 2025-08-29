package com.example.hotel.demo;

import com.example.hotel.demo.entity.Hotel;
import com.example.hotel.demo.entity.HotelDoc;
import com.example.hotel.demo.service.IHotelService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class EsTest_02_Document {
    private RestHighLevelClient client;
    @Autowired
    private IHotelService iHotelService;

    private ObjectMapper objectMapper;

    private final String indexName = "hotel"; // es 中的数据库名称

    private final String docId = "60223"; // Mysql 数据库中的 id，es 中的 docId 保持一致

    private final String host = "http://localhost:9200"; // es 服务器的地址

    /**
     * 从 Mysql 中批量导入数据
     * 
     * @throws IOException
     */
    @Test
    public void testBatchUpdateDoc() throws IOException {
        // 1. 从 Mysql 数据库中查询数据
        List<Hotel> list = iHotelService.list();
        System.out.println("查询到的数据的条数为：" + list.size());
        // 2. 构建 request 的 Body 内容
        BulkRequest request = new BulkRequest(indexName);
        for (Hotel hotel : list) {
            HotelDoc hotelDoc = new HotelDoc(hotel);
            request.add(new IndexRequest(indexName)
                    .id(hotel.getId().toString())
                    .source(objectMapper.writeValueAsString(hotelDoc), XContentType.JSON));
        }
        // 3. client 发送请求
        client.bulk(request, RequestOptions.DEFAULT);
    }

    /**
     * 根据 id 删除文档
     * 
     * @throws IOException
     */
    @Test
    public void testDeleteDocById() throws IOException {
        DeleteRequest request = new DeleteRequest(indexName, docId);
        client.delete(request, RequestOptions.DEFAULT);
    }

    /**
     * 根据 id 增量修改文档 （全量修改文档与新增文档一样）
     * POST /hotel/_doc/61083
     * 
     * @throws IOException
     */
    @Test
    public void testUpdateDoc() throws IOException {
        UpdateRequest request = new UpdateRequest(indexName, docId);
        request.doc("starName", "四钻", "price", "972");
        client.update(request, RequestOptions.DEFAULT);
    }

    /**
     * 根据 id 查询文档，并使用 objeactMapper 反序列化
     * 
     * @throws IOException
     */
    @Test
    public void testGetDoc() throws IOException {
        GetRequest request = new GetRequest(indexName, docId);
        GetResponse documentFields = client.get(request, RequestOptions.DEFAULT);
        String json = documentFields.getSourceAsString();
        System.out.println("未序列化的数据为 \n"+json);
        if (json != null) {
            HotelDoc hotelDoc = objectMapper.readValue(json, HotelDoc.class);
            System.out.println("序列化后的数据为 \n"+hotelDoc);
        }
    }

    /**
     * 插入单条 HotelDoc 数据
     * 重复执行不会出错，因为相当于【全量新增】
     * 
     * @throws IOException
     */
    @Test
    public void testAddDoc() throws IOException {
        // 1. 向 Mysql 数据库中获取单条数据
        Hotel hotel = iHotelService.getById(docId);
        HotelDoc hotelDoc = new HotelDoc(hotel);
        System.out.println(hotelDoc);
        // 2. 创建 request 对象
        IndexRequest request = new IndexRequest(indexName).id(docId);
        // 3. 准备 Json 文档，并导入到 request 中
        String docString = objectMapper.writeValueAsString(hotelDoc);
        request.source(docString, XContentType.JSON);
        // 4. 发送请求
        client.index(request, RequestOptions.DEFAULT);
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
