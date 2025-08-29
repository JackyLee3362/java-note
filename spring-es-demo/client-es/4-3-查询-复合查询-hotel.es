// 算分查询
GET /hotel/_search
{
    "query": {
        "function_score": {
            "query": {
                "match": {
                    "all": "外滩"
                }
            },
            "functions": [
                {
                    "filter": {
                        "term": {
                            "brand": "如家"
                        }
                    },
                    "weight": 10
                }
            ],
            "boost_mode": "sum"
        }
    }
}

// 布尔查询
GET /hotel/_search
{
  "query": {
    "bool": {
      "must": [
        {"term": {"city": "上海" }}
      ],
      "should": [
        {"term": {"brand": "皇冠假日" }},
        {"term": {"brand": "华美达" }}
      ],
      "must_not": [
        { "range": { "price": { "lte": 500 } }}
      ],
      "filter": [
        { "range": {"score": { "gte": 45 } }}
      ]
    }
  }
}

// 布尔查询2
GET /hotel/_search
{
  "query": { // 需求：搜索名字包含“如家”，价格不高于400，在坐标31.21,121.5周围10km范围内的酒店。
    "bool": {
      "must": [
        {
          "match":{"name": "如家"}
        }
      ],
      "must_not": [
        {
          "range":{
            "price": {"gte": 400}
          }
        }
      ],
      "filter": [
        {"geo_distance": {
          "distance": "10km",
          "location": {
            "lat": 31.21,
            "lon": 121.5
          }
        }}
      ]
    }
  }
}   

// 排序
GET /hotel/_search
{
  "query": {
    "match_all": {}
  },
  "sort": [
    {
      "_geo_distance" : {
          "location" : "31.21,121.5", // 文档中geo_point类型的字段名、目标坐标点
          "order" : "asc", // 排序方式
          // "order" : "desc", // 排序方式
          "unit" : "km" // 排序的距离单位
      }
    }
  ]
}

// 分页
GET /hotel/_search
{
  "query": {
    "match_all": {}
  },
  "from": 0, // 分页开始的位置，默认为0
  "size": 3, // 期望获取的文档总数
  "sort": [
    {
      "price": "asc"
    }
  ]
}

// 高亮
GET /hotel/_search
{
  "query": {
    "match": {
      "all": "如家" // 查询条件，高亮一定要使用全文检索查询
    }
  },
  "highlight": {
    "fields": { // 指定要高亮的字段
      "name": {
        "require_field_match":"false"
      }
    }
  }
}