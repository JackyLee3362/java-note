// 新增文档 put/post都可
POST /heima/_doc/1
{
    "info": "黑马程序员Java讲师",
    "email": "zy@itcast.cn",
    "name": {
        "firstName": "云",
        "lastName": "赵"
    }
}

// 查询文档
GET /heima/_doc/1

// 删除文档
DELETE /heima/_doc/1

// 全量修改文档 put/post 都可
PUT /heima/_doc/1
{
  "info": "黑马程序员高级Java讲师",
  "name": {
    "firstName": "刘",
    "lastName": "备"
  }
}

// 增量修改文档 update!!!!
POST /heima/_update/1
{
    "doc": {
        "email": "Liubei@itcast.cn"
    }
}