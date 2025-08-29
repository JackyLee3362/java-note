// 查询所有文档
GET /hotel/_search
{
    "query": {
        "match_all": {}
    }
}

// 单字段查询
GET /hotel/_search
{
    "query": {
        "match": {
            "all": "外滩" // 字段名为【all】，值为【外滩】
        }
    }
}

// 选择多个字段，在其中查询
GET /hotel/_search
{
    "query": {
        "multi_match": {
            "query": "外滩如家",
            "fields": [
                "brand",
                "name",
                "business"
            ]
        }
    }
}