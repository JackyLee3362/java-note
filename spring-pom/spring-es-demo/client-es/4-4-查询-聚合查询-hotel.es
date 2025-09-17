// Bucket 聚合查询
GET /hotel/_search
{
    "size": 0, // 设置size为0，结果中不包含文档，只包含聚合结果
    "aggs": { // 定义聚合
        "brandAgg": { //给聚合起个名字
            "terms": { // 聚合的类型，按照品牌值聚合，所以选择term
                "field": "brand", // 参与聚合的字段
                // "order": {"_count": "asc"}, // 默认按照降序，可以手动设置升序
                "size": 3 // 希望获取的聚合结果数量
            }
        }
    }
}

// Bucket 聚合查询-限定范围
GET /hotel/_search
{
    "query": {
        "range": {
            "price": {
                "gte": 100,
                "lte": 400
            }
        }
    },
    "size": 0, // 设置size为0，结果中不包含文档，只包含聚合结果
    "aggs": { // 定义聚合
        "brandAgg": { //给聚合起个名字
            "terms": { // 聚合的类型，按照品牌值聚合，所以选择term
                "field": "brand", // 参与聚合的字段
                // "order": {"_count": "asc"}, // 默认按照降序，可以手动设置升序
                "size": 10 // 希望获取的聚合结果数量
            }
        }
    }
}

// Metric 聚合
GET /hotel/_search
{
    "size": 0,
    "aggs": {
        "brandAgg": {
            "terms": {
                "field": "brand",
                "size": 20,
                "order":{
                    "scoreAgg.avg": "desc"
                }
            },
            "aggs": { // 是brands聚合的子聚合，也就是分组后对每组分别计算
                "scoreAgg": { // 聚合名称
                    "stats": { // 聚合类型，这里stats可以计算min、max、avg等
                        "field": "score" // 聚合字段，这里是score
                    }
                }
            }
        }
    }
}

// Bucket 多个聚合
GET /hotel/_search
{
    // 对品牌、城市、星级的聚合
    "size": 0, // 设置size为0，结果中不包含文档，只包含聚合结果
    "aggs": { // 定义聚合
        "brandAgg": { //给聚合起个名字
            "terms": { // 聚合的类型，按照品牌值聚合，所以选择term
                "field": "brand", // 参与聚合的字段
                // "order": {"_count": "asc"}, // 默认按照降序，可以手动设置升序
                "size": 20 // 希望获取的聚合结果数量
            }
        },
        "cityAgg": { //给聚合起个名字
            "terms": { // 聚合的类型，按照品牌值聚合，所以选择term
                "field": "city", // 参与聚合的字段
                // "order": {"_count": "asc"}, // 默认按照降序，可以手动设置升序
                "size": 20 // 希望获取的聚合结果数量
            }
        }
    }
}