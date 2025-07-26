package edu.note.spring.redis;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.lang.NonNull;

/**
 * @description: 测试 redis 基本数据类型的操作
 * @author: Jacky Lee
 * @date: 2024/3/22 23:13
 */
@SpringBootTest
class RedisDemoApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @description: Redis 中操作【String】
     *               <p>
     *               <a href=
     *               "https://javaguide.cn/database/redis/redis-data-structures-01.html#string-%E5%AD%97%E7%AC%A6%E4%B8%B2">Redis
     *               5 种基本数据类型详解 | JavaGuide</a>
     *               <p>
     *               <a href="https://redis.io/commands/?group=string">Commands |
     *               Redis</a>
     * @author: Jacky Lee
     * @date: 2024/3/23 0:45
     */
    @Test
    void testRedisOpsForString() {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        String key = "some_key";
        String expiredKey = "key_expired";
        // set key value
        valueOperations.set(key, "123456");
        // setex key seconds value
        valueOperations.set(expiredKey, "很快就要过期......", 10, TimeUnit.SECONDS);
        // setnx key value 当 key 不存在时设置
        valueOperations.setIfAbsent(key, "111111");
        // setex key value 当 key 存在时设置
        valueOperations.setIfPresent(key, "222222");
        // get key
        System.out.println(valueOperations.get(key));

    }

    /**
     * @description: Redis 操作【List】
     *               <p>
     *               <a href=
     *               "https://javaguide.cn/database/redis/redis-data-structures-01.html#list-%E5%88%97%E8%A1%A8">Redis
     *               5 种基本数据类型详解 | JavaGuide</a>
     * @author: Jacky Lee
     * @date: 2024/3/23 0:55
     */
    @Test
    void testRedisOpsForList() {
        String mylist = "mylist";
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
        // lpush
        listOperations.leftPush(mylist, "111");
        listOperations.leftPush(mylist, "222");
        // lrange
        // rpop
        // llen
        System.out.println(listOperations.leftPop(mylist));
    }

    /**
     * @description: Redis 操作【Hash】
     *               <p>
     *               <a href=
     *               "https://javaguide.cn/database/redis/redis-data-structures-01.html#hash-%E5%93%88%E5%B8%8C">Redis
     *               5 种基本数据类型详解 | JavaGuide</a>
     * @author: Jacky Lee
     * @date: 2024/3/23 0:54
     */
    @Test
    void testRedisOpsForHash() {
        HashOperations<String, Object, Object> hashOperation = stringRedisTemplate.opsForHash();
        String key = "泰勒斯威夫特";
        // hset hget hdel hkeys hvals
        // hset key field value 设置指定哈希表中指定字段的值
        hashOperation.put(key, "name", "霉霉");
        hashOperation.put(key, "age", "24");
        // hkeys
        System.out.println(hashOperation.keys(key));
        // hvals
        System.out.println(hashOperation.values(key));
        // hget
        System.out.println(hashOperation.get(key, "name"));
        // hdel
        hashOperation.delete(key, "age");
    }

    /**
     * @description: Redis 中操作【Set】
     *               <p>
     *               <a href=
     *               "https://javaguide.cn/database/redis/redis-data-structures-01.html#set-%E9%9B%86%E5%90%88">Redis
     *               5 种基本数据类型详解 | JavaGuide</a>
     * @author: Jacky Lee
     * @date: 2024/3/23 0:49
     */
    @Test
    void testRedisOpsForSet() {
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
        String key = "myset";
        // sadd key member1 member2 ...
        setOperations.add(key, "s1", "s2", "s3");
        // smembers key 获取指定集合中的所有元素
        System.out.println(setOperations.members(key));
        // spop key 随机移除一个
        System.out.println(setOperations.pop(key));
    }

    /**
     * @description: Redis 中操作【Zset】
     *               <p>
     *               <a href=
     *               "https://javaguide.cn/database/redis/redis-data-structures-01.html#sorted-set-%E6%9C%89%E5%BA%8F%E9%9B%86%E5%90%88">Redis
     *               5 种基本数据类型详解 | JavaGuide</a>
     * @author: Jacky Lee
     * @date: 2024/3/23 0:57
     */
    @Test
    void testRedisOpsForZSet() {
        ZSetOperations<String, String> ZSetOperations = stringRedisTemplate.opsForZSet();
        String key = "myzset";
        // zadd key score1 member1 score2 member2 ... 向有序集合中添加元素
        ZSetOperations.add(key, "华为", 12);
        ZSetOperations.add(key, "苹果", 10);
        ZSetOperations.add(key, "小米", 8);
        // zscore key member 获取指定有序集合中指定元素的 score 值
        System.out.println("获得华为的分数是 " + ZSetOperations.score(key, "华为"));
        // zcard key 获取有序集合数量
        System.out.println("元素中的数量是" + ZSetOperations.zCard(key));
        // zrange key start end 获取指定有序集合 start 和 end 之间的元素（score 从低到高）
        System.out.println("score升序:" + ZSetOperations.range(key, 0, 10));
        // zrevrange key start end 获取指定有序集合 start 和 end 之间的元素（score 从高到底）
        System.out.println("score降序:" + ZSetOperations.reverseRange(key, 0, 10));
        // zrank key member 获取指定有序集合中指定元素的排名(score 从大到小排序)
        System.out.println("华为的分数的排名是（从0开始，从小到大排名）" + ZSetOperations.rank(key, "华为"));
        // zrevrank key member 获取指定有序集合中指定元素的排名(score 从大到小排序)
        System.out.println("华为的分数的排名是（从0开始，从大到小排名）" + ZSetOperations.reverseRank(key, "华为"));

    }

    /**
     * @description: Redis 开启事务，Redis不支持回滚，运行后会变成 key=123，即使一开始 num 并不存在
     * @author: Jacky Lee
     * @date: 2024/3/23 0:53
     */
    @Test
    @Disabled(value="会存在异常")
    void testRedisTransaction() {
        // redis 事务
        stringRedisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(@NonNull RedisConnection redisConnection) throws DataAccessException {
                String key = "num";
                redisConnection.multi();
                redisConnection.set(key.getBytes(), "123".getBytes());
                redisConnection.hSet(key.getBytes(), "456".getBytes(), "789".getBytes());
                return redisConnection.exec().toString();
            }
        });

    }

}
