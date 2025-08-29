// 删除 test 索引库
DELETE /test

// 创建自动补全的索引库
PUT /test
{
    "mappings": {
        "properties": {
            "title": {
                "type": "completion"
            }
        }
    }
}
// 示例数据 1
POST /test/_doc/1
{
  "title": ["Sony", "WH-1000XM3"]
}
// 示例数据 2
POST /test/_doc/2
{
  "title": ["SK-II", "PITERA"]
}
// 示例数据 3
POST /test/_doc/3
{
  "title": ["Nintendo", "switch"]
}

// 查询所有文档
GET /test/_search

// 自动补全查询
POST /test/_search
{
  "suggest": {
    "title_suggest": {
      "text": "so", // 关键字 e.g. "s", "so"
      "completion": {
        "field": "title", // 补全字段
        "skip_duplicates": true, // 跳过重复的
        "size": 10 // 获取前10条结果
      }
    }
  }
}

