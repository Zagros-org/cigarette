package com.cigarette;


import redis.clients.jedis.Jedis;

class RedisCliTest {


    public static void main(String[] args) {
        Jedis client = new RedisCli().getClient();
        client.set("1", "1111");
    }

}