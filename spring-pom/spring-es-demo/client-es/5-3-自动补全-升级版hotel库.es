// 删除索引库
DELETE /hotel

// 创建索引库
PUT /hotel
{
    "settings": {
        "analysis": {
            "analyzer": {
                "text_anlyzer": {
                    "tokenizer": "ik_max_word",
                    "filter": "py"
                },
                "completion_analyzer": {
                    "tokenizer": "keyword",
                    "filter": "py"
                }
            },
            "filter": {
                "py": {
                    "type": "pinyin",
                    "keep_full_pinyin": false,
                    "keep_joined_full_pinyin": true,
                    "keep_original": true,
                    "limit_first_letter_length": 16,
                    "remove_duplicated_term": true,
                    "none_chinese_pinyin_tokenize": false
                }
            }
        }
    },
    "mappings": {
        "properties": {
            "id": {
                "type": "keyword"
            },
            "name": {
                "type": "text",
                "analyzer": "text_anlyzer",
                "search_analyzer": "ik_smart",
                "copy_to": "all"
            },
            "address": {
                "type": "keyword",
                "index": false
            },
            "price": {
                "type": "integer"
            },
            "score": {
                "type": "integer"
            },
            "brand": {
                "type": "keyword",
                "copy_to": "all"
            },
            "city": {
                "type": "keyword"
            },
            "starName": {
                "type": "keyword"
            },
            "business": {
                "type": "keyword",
                "copy_to": "all"
            },
            "location": {
                "type": "geo_point"
            },
            "pic": {
                "type": "keyword",
                "index": false
            },
            "all": {
                "type": "text",
                "analyzer": "text_anlyzer",
                "search_analyzer": "ik_smart"
            },
            "suggestion": {
                "type": "completion",
                "analyzer": "completion_analyzer"
            }
        }
    }
}

// 查询所有文档
GET /hotel/_search

// 测试自动补全功能
GET /hotel/_search
{
  "suggest":{
    "suggestions":{ // 补全名
      "text": "h",
      "completion":{
        "field":"suggestion",
        "skip_duplicates": true,
        "size":10
      }
    }
  }
}