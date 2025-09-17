// 查询所有文档
GET /hotel/_search
{
    "query": {
        "match_all": {}
    }
}

// 根据 id 查询文档
GET /hotel/_doc/36934

