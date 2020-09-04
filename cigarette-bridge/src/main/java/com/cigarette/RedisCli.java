package com.cigarette;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Closeable;

/**
 * redis 连接数据源
 *
 * @author LDZ
 * @date 2020/9/4 3:22 下午
 */
public class RedisCli implements Closeable {

    /**
     * pool
     */
    private JedisPool pool;

    /**
     * jedis
     */
    private volatile static Jedis client;

    public RedisCli() {
        init();
    }

    public Jedis getClient() {
        if (client == null) {
            synchronized (RedisCli.class) {
                if (client == null) {
                    client = this.pool.getResource();
                }
            }
        }
        return client;
    }

    /**
     * init
     * todo 项目初期 先把这里写死，后续将这里配置到Spring中
     *
     * @return redis cli
     */
    private RedisCli init() {
        final JedisPoolConfig config = new JedisPoolConfig();
        this.pool = new JedisPool(
                config,
                "106.54.200.51",
                6379,
                2000,
                "112233"
        );
        return this;
    }

    @Override
    public void close() {
        if (pool != null) {
            try {
                pool.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }
}
