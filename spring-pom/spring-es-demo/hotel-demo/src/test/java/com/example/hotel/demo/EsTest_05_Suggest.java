package com.example.hotel.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EsTest_05_Suggest {
    private RestHighLevelClient client;
    // @Autowired
    // private IHotelService iHotelService;

    private ObjectMapper objectMapper;

    @Test
    public void testSuggestion() throws IOException {

        SearchRequest request = new SearchRequest("hotel");
        CompletionSuggestionBuilder suggestionBuilder = SuggestBuilders
                .completionSuggestion("suggestion")
                .prefix("h") // 使用 "hs" 或者 "h" 测试
                .skipDuplicates(true)
                .size(10);
        SuggestBuilder suggestBuilder = new SuggestBuilder().addSuggestion("mySuggestion", suggestionBuilder);
        request.source().suggest(suggestBuilder);
        // 解析结果
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println(response);
        Suggest suggest = response.getSuggest();
        CompletionSuggestion suggestion = suggest.getSuggestion("mySuggestion");
        for (CompletionSuggestion.Entry.Option option : suggestion.getOptions()) {
            String string = option.getText().toString();
            System.out.println(string);
        }
    }

    @BeforeEach
    public void setUp() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.1.110:9200")));
        this.objectMapper = new ObjectMapper();
    }

    @AfterEach
    public void tearDown() throws IOException {
        this.client.close();
    }
}
