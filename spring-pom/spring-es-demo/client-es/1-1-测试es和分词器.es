// 查看es是否启动成功
GET /

// 测试分词器


// 英文分词器
GET /_analyze
{
    "text":"黑马程序员真是太棒了",
    "analyzer": "english"
}

// ik_smart 分词器
GET /_analyze
{
    "text": "原神很好玩，但是感觉不如崩铁和明日方舟",
    "analyzer": "ik_smart"
}

GET /_analyze
{
    "text": "黑马程序员真是太棒了",
    "analyzer": "ik_max_word"
}

GET /_analyze
{
    "text":"黑马程序员真是太棒了1",
    "analyzer": "pinyin"
}