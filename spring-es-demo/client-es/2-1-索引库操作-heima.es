// 创建索引库
PUT /heima
{
    "mappings": {
        "properties": {
            "info": {
                "type": "text",
                "analyzer": "ik_smart"
            },
            "email": {
                "type": "keyword",
                "index": "false"
            },
            "name": {
                "properties": {
                    "firstName": {
                        "type": "keyword"
                    },
                    "lastName": {
                        "type": "keyword"
                    }
                }
            }
        }
    }
}

// 查询索引库
GET /heima

// 删除索引库
DELETE /heima

// 修改索引库，添加新字段
PUT /heima/_mapping
{
    "properties": {
        "year": {
            "type": "integer"
        }
    }
}