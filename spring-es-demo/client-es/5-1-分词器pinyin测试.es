// 查询索引库
// 
PUT /test
{
    "settings": {
        "analysis": {
            "analyzer": {
                "my_analyzer": {
                    "tokenizer": "ik_max_word",
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
            "name": {
                "type": "text",
                "analyzer": "my_analyzer",
                "search_analyzer": "ik_smart"
            }
        }
    }
}

// 查询索引库
GET /test

// 查找全部文档
GET /test/_search

// 创建文档 1
POST /test/_doc/1
{
  "id": 1,
  "name": "狮子"
}


// 创建文档 2
POST /test/_doc/2
{
  "id": 2,
  "name": "虱子"
}

// 查询文档 
GET /test/_search
{
  "query": {
    "match": {
      "name": "掉入狮子笼咋办"
      //"name": "shizi"
    }
  }
}

// 删除数据库 test
DELETE /test