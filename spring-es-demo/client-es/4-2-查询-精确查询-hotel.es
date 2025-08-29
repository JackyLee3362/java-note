// Term 关键字查询
GET /hotel/_search
{
    "query": {
        "term": {
            "city": {
                "value": "上海"
            }
        }
    }
}

// Range 范围查询
GET /hotel/_search
{
    "query":{
        "range":{
            "price":{
                "gte":100,
                "lte":200
            }
        }
    }
}

// Geo 矩阵查询
GET /hotel/_search
{
  "query": {
    "geo_bounding_box": {
      "location": {
        "top_left": { // 左上点
          "lat": 31.1,
          "lon": 121.5
        },
        "bottom_right": { // 右下点
          "lat": 30.9,
          "lon": 121.7
        }
      }
    }
  }
}

// Geo 距离查询
GET /hotel/_search
{
    "query":{
        "geo_distance":{
            "distance":"15km",
            "location":"31.21,121.5"
        }
    }
}