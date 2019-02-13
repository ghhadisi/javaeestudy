package com.hss.jredis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Test {

    public static void main(String[] args) {
//        Jedis jedis = new Jedis("127.0.0.1",6379);
//        jedis.set("name", "zhangsan");
//
//        System.out.println(jedis.get("name"));
//        jedis.close();

        redisPool();
    }
    public  static void redisPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        //最大空闲连接数
        config.setMaxIdle(10);
        //最大连接束
        config.setMaxTotal(30);

        JedisPool jedisPool = new JedisPool(config,"127.0.0.1",6379);
        Jedis jedis = jedisPool.getResource();
        jedis.set("name", "lisi");

        System.out.println(jedis.get("name"));
        jedis.close();
        if (jedisPool !=null){
            jedisPool.close();
        }
    }

}
