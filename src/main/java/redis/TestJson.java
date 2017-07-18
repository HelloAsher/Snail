package redis;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

/**
 * Created by yzbfl on 2017-07-18.
 */
public class TestJson {
    private static AuthorizeInfo json;

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        setJson();
        long endTime = System.nanoTime();
        System.out.println((float) (endTime - startTime) / 1000000 + "ms");

        long startTime2 = System.nanoTime();
        AuthorizeInfo authorizeInfo = getJson();
        long endTime2 = System.nanoTime();
        System.out.println((float) (endTime2 - startTime2) / 1000000 + "ms");
        System.out.println(authorizeInfo);
    }

    private static void setJson() {
        String tableName = "json";

        StringBuilder key = new StringBuilder();
        key.append("callerId");
        key.append("|callerAuthKey");

        AuthorizeInfo authorizeInfo = new AuthorizeInfo();
        authorizeInfo.setIp("192.168.111.112");
        authorizeInfo.setOther1("other1");
        authorizeInfo.setOther2("other2");
        authorizeInfo.setOther3("other3");
        authorizeInfo.setOther4("other4");
        authorizeInfo.setOther5("other5");
        authorizeInfo.setOther6("other6");
        authorizeInfo.setOther7("other7");
        authorizeInfo.setOther8("other8");
        authorizeInfo.setOther9("other9");

        String jsonString = JSON.toJSONString(authorizeInfo);
        Jedis jedis = null;
        try {
            jedis = JedisPoolManager.getInstance().getResource();
            jedis.hset(tableName, key.toString(), jsonString);
        } finally {
            jedis.close();
        }
    }

    public static AuthorizeInfo getJson() {
        AuthorizeInfo authorizeInfo;
        Jedis jedis = null;
        try {
            jedis = JedisPoolManager.getInstance().getResource();
            String value = jedis.hget("json", "callerId|callerAuthKey");
            authorizeInfo = JSON.parseObject(value, AuthorizeInfo.class);
        } finally {
            jedis.close();
        }
        return authorizeInfo;
    }
}
