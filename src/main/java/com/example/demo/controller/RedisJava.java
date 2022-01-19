package com.example.demo.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.Format;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redisTest")
public class RedisJava {

    /**
     *
     * 当redis数据库里本来存的是字符串数据或者你要存取的数据就是字符串类型的数据时使用StringRedisTemplate
     * 但是如果是复杂的对象类型，而且取出的时候又不想有任何数据转换，直接从Redis里取出对象，那么使用RedisTemplate
     * 两种类型的数据不能互相通用
     */
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //保存键值对
    @GetMapping("save")
    public void saveTest(){

    }
    @GetMapping("test")
    public String test(String key){

      //  redisTemplate.opsForValue().set("cq","LuQuan",100,TimeUnit.SECONDS);
        //stringRedisTemplate.opsForValue().set("cq001","LuQuan001",300,TimeUnit.SECONDS);
        /*stringRedisTemplate.opsForList().leftPush("cq", "disappear" ,"disappear2");
        stringRedisTemplate.opsForValue().set("cq001","LuQuan002");*/
        /*List list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(44);
        list.add(1231);
        redisTemplate.opsForValue().set("CQ",list);*/
        //System.out.println(stringRedisTemplate.opsForValue().get("cq001"));
        //return expire?"成功":"失败";
        //redisTemplate.delete("CQ");
/*        stringRedisTemplate.opsForValue().set("test:cq003","块乐星球3");
        stringRedisTemplate.opsForValue().set("test:cq004","块乐星球4");
        stringRedisTemplate.opsForValue().set("test:cq005","块乐星球");
        stringRedisTemplate.opsForValue().set("test:cq006","块乐星球");
        stringRedisTemplate.opsForValue().set("test:cq002","块乐星球2");*/
        //System.out.println(redisTemplate.opsForValue().get("CQ"));
        /*stringRedisTemplate.delete("test:cq002");
        stringRedisTemplate.delete("test:cq003");
        stringRedisTemplate.delete("test:cq004");
        stringRedisTemplate.delete("test:cq005");
        stringRedisTemplate.delete("test:cq006");*/
        stringRedisTemplate.opsForValue().set("free:bai","bai",30,TimeUnit.SECONDS);
        return "null";

    }



    /**
     * 实现命令：TTL key，以秒为单位，返回给定 key的剩余生存时间(TTL, time to live)。
     *
     * @param key
     * @return
     */
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 实现命令：expire 设置过期时间，单位秒
     *
     * @param key
     * @return
     */
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：INCR key，增加key一次
     *
     * @param key
     * @return
     */
    public long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 实现命令：KEYS pattern，查找所有符合给定模式 pattern 的 key
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 实现命令：DEL key，删除一个key
     *
     * @param key
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    // String（字符串）

    /**
     * 实现命令：SET key value，设置一个key-value（将字符串值 value关联到 key）
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
     *
     * @param key
     * @param value
     * @param timeout
     *            （以秒为单位）
     */
    public void set(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：GET key，返回 key所关联的字符串值。
     *
     * @param key
     * @return value
     */
    public String get(String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }

    // Hash（哈希表）

    /**
     * 实现命令：HSET key field value，将哈希表 key中的域 field的值设为 value
     *
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        return (String) redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @param key
     * @param fields
     */
    public void hdel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hgetall(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    // List（列表）

    /**
     * 实现命令：LPUSH key value，将一个值 value插入到列表 key的表头
     *
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long lpush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 实现命令：LPOP key，移除并返回列表 key的头元素。
     *
     * @param key
     * @return 列表key的头元素。
     */
    public String lpop(String key) {
        return (String)redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 实现命令：RPUSH key value，将一个值 value插入到列表 key的表尾(最右边)。
     *
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long rpush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

}
