package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by yzbfl on 2017-07-17.
 */
public class JedisPoolManager {
    private volatile static JedisPoolManager jedisPoolManager;
    private final JedisPool pool;

    public JedisPoolManager() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(8);
        poolConfig.setMaxIdle(4);
        this.pool = new JedisPool(poolConfig, "192.168.177.132", 6379);
    }

    public static JedisPoolManager getInstance(){
        if(jedisPoolManager == null){
            synchronized (JedisPoolManager.class){
                if(jedisPoolManager == null){
                    jedisPoolManager = new JedisPoolManager();
                }
            }
        }
        return jedisPoolManager;
    }

    public Jedis getResource(){
        return this.pool.getResource();
    }

    public void close(){
        pool.close();
    }
}
