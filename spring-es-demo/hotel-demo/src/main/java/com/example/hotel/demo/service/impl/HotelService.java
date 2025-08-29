package com.example.hotel.demo.service.impl;

import com.example.hotel.demo.common.PageResult;
import com.example.hotel.demo.mapper.HotelMapper;
import com.example.hotel.demo.entity.Hotel;
import com.example.hotel.demo.entity.HotelDoc;
import com.example.hotel.demo.entity.RequestDTO;
import com.example.hotel.demo.service.IHotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {
    @Autowired
    RestHighLevelClient client;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    IHotelService iHotelService;
    private final String HOTEL = "hotel";

    @Override
    public PageResult search(RequestDTO requestDTO) {
        SearchRequest request = new SearchRequest(HOTEL);
        // 2. 构造DSL
        buildBoolQuery(requestDTO, request);
        // 2.3 分页
        Integer page = requestDTO.getPage();
        Integer size = requestDTO.getSize();
        request.source().from((page - 1) * size).size(size);
        // 3. 发送请求
        try {
            // 4 解析结果
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            return parseResponse(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, List<String>> filters(RequestDTO data) {
        try {
            // 1.准备Request
            SearchRequest request = new SearchRequest(HOTEL);
            // 2.准备DSL
            // 2.1.query
            buildBoolQuery(data, request);
            // 2.2.设置size
            request.source().size(0);
            // 2.3.聚合
            buildAggregation(request);
            // 3.发出请求
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            // 4.解析结果
            Map<String, List<String>> result = new HashMap<>();
            Aggregations aggregations = response.getAggregations();
            // 4.1.根据【品牌】名称，获取品牌结果
            List<String> brandList = getAggByName(aggregations, "brandAgg");
            result.put("brand", brandList);
            // 4.2.根据【城市】名称，获取品牌结果
            List<String> cityList = getAggByName(aggregations, "cityAgg");
            result.put("city", cityList);
            // 4.3.根据【星级】名称，获取品牌结果
            List<String> starList = getAggByName(aggregations, "starAgg");
            result.put("starName", starList);

            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> suggestion(String key) {
        SearchRequest request = new SearchRequest(HOTEL);
        CompletionSuggestionBuilder suggestionBuilder = SuggestBuilders
                .completionSuggestion("suggestion")
                .prefix(key)
                .skipDuplicates(true)
                .size(10);
        SuggestBuilder suggestBuilder = new SuggestBuilder().addSuggestion("mySuggestion", suggestionBuilder);
        request.source().suggest(suggestBuilder);
        try {
            // 发送请求
            List<String> result = new ArrayList<>();
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            System.out.println(response);
            // 解析结果
            Suggest suggest = response.getSuggest();
            CompletionSuggestion suggestion = suggest.getSuggestion("mySuggestion");
            for (CompletionSuggestion.Entry.Option option : suggestion.getOptions()) {
                String string = option.getText().toString();
                result.add(string);
                // System.out.println(string);
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param id
     * @description: 根据 id 在 elasticSearch 中新增相关 Hotel
     * @return: void
     * @author: Jacky Lee
     * @date: 2024/1/21 22:58
     */
    @Override
    public void insertById(Long id) {
        try {
            // 根据id查询
            Hotel hotel = iHotelService.getById(id);
            HotelDoc hotelDoc = new HotelDoc(hotel);
            // 构造请求
            IndexRequest request = new IndexRequest(HOTEL).id(id.toString());
            // 请求参数设置json
            String s = objectMapper.writeValueAsString(hotelDoc);
            System.out.println(s);
            request.source(s, XContentType.JSON);
            // 发送请求
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @param id
     * @description: 根据hotel的id在es中删除文档
     * @return: void
     * @author: Jacky Lee
     * @date: 2024/1/21 23:03
     */
    @Override
    public void deleteById(Long id) {
        try {
            DeleteRequest request = new DeleteRequest(HOTEL, id.toString());
            client.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 根据聚合名称获取聚合结果
     * 
     * @param aggregations
     * @param aggName
     * @return
     */
    private List<String> getAggByName(Aggregations aggregations, String aggName) {
        // 4.1.根据聚合名称获取聚合结果
        Terms brandTerms = aggregations.get(aggName);
        // 4.2.获取buckets
        List<? extends Terms.Bucket> buckets = brandTerms.getBuckets();
        // 4.3.遍历
        List<String> brandList = new ArrayList<>();
        for (Terms.Bucket bucket : buckets) {
            // 4.4.获取key
            String key = bucket.getKeyAsString();
            brandList.add(key);
        }
        return brandList;
    }

    private void buildAggregation(SearchRequest request) {
        request.source().aggregation(AggregationBuilders
                .terms("brandAgg")
                .field("brand")
                .size(100));
        request.source().aggregation(AggregationBuilders
                .terms("cityAgg")
                .field("city")
                .size(100));
        request.source().aggregation(AggregationBuilders
                .terms("starAgg")
                .field("starName")
                .size(100));
    }

    private static void buildBoolQuery(RequestDTO requestDTO, SearchRequest request) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        // 2.1 关键字搜索
        String key = requestDTO.getKey();
        if (StringUtils.isEmpty(key)) {
            boolQuery.must(QueryBuilders.matchAllQuery());
        } else {
            boolQuery.must(QueryBuilders.matchQuery("all", key));
        }
        // 2.2 过滤
        String city = requestDTO.getCity();
        String brand = requestDTO.getBrand();
        String starName = requestDTO.getStarName();
        Integer minPrice = requestDTO.getMinPrice();
        Integer maxPrice = requestDTO.getMaxPrice();
        if (!StringUtils.isEmpty(city)) {
            boolQuery.filter(QueryBuilders.termQuery("city", city));
        }
        if (!StringUtils.isEmpty(brand)) {
            boolQuery.filter(QueryBuilders.termQuery("brand", brand));
        }
        if (!StringUtils.isEmpty(starName)) {
            boolQuery.filter(QueryBuilders.termQuery("starName", starName));
        }
        if (Objects.nonNull(minPrice)) {
            boolQuery.filter(QueryBuilders.rangeQuery("price").gt(minPrice));
        }
        if (Objects.nonNull(maxPrice)) {
            boolQuery.filter(QueryBuilders.rangeQuery("price").lt(maxPrice));
        }
        // 获取附件的酒店
        String location = requestDTO.getLocation();
        if (!StringUtils.isEmpty(location)) {
            GeoPoint geoPoint = new GeoPoint(location);
            GeoDistanceSortBuilder geoLocation = new GeoDistanceSortBuilder("location", geoPoint)
                    .order(SortOrder.ASC)
                    .unit(DistanceUnit.KILOMETERS);
            request.source().sort(geoLocation);
            // boolQuery.filter(QueryBuilders.geoDistanceQuery("location").)
        }
        // 算分
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = {
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                        QueryBuilders.termQuery("isAD", true),
                        ScoreFunctionBuilders.weightFactorFunction(50))
        };
        FunctionScoreQueryBuilder functionScoreQuery = QueryBuilders.functionScoreQuery(
                boolQuery, filterFunctionBuilders);
        request.source().query(functionScoreQuery);
        // request.source().query(boolQuery);

    }

    private PageResult parseResponse(SearchResponse response) throws JsonProcessingException {
        // 获得总长度
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        // 设置总长度
        System.out.printf("总共的记录数为%d\n", total);
        SearchHit[] hits = searchHits.getHits();
        // 获得列表
        List<HotelDoc> hotelDocList = new ArrayList<>();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            HotelDoc hotelDoc = objectMapper.readValue(json, HotelDoc.class);
            Object[] sortValues = hit.getSortValues();
            if (sortValues.length > 0) {
                hotelDoc.setDistance(sortValues[0]);
            }

            hotelDocList.add(hotelDoc);
            System.out.println(hotelDoc);
        }
        return new PageResult(total, hotelDocList);
    }
}
