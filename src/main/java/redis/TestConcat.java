package redis;

import redis.clients.jedis.Jedis;

/**
 * Created by SCAL on 2017/7/18.
 */
public class TestConcat {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        setString();
        long endTime = System.nanoTime();
        System.out.println((float) (endTime - startTime) / 1000000 + "ms");

        long startTime2 = System.nanoTime();
        AuthorizeInfo authorizeInfo = getString();
        long endTime2 = System.nanoTime();
        System.out.println((float) (endTime2 - startTime2) / 1000000 + "ms");
        System.out.println(authorizeInfo);
    }

    private static void setString() {
        String tableName = "string";

        StringBuilder key = new StringBuilder();
        key.append("callerId");
        key.append("|callerAuthKey");

        StringBuilder value = new StringBuilder();
        value.append("192.168.111.112|");
        value.append("other1|");
        value.append("other2|");
        value.append("other3|");
        value.append("other4|");
        value.append("other5|");
        value.append("other6|");
        value.append("other7|");
        value.append("other8|");
        value.append("other9");

        Jedis jedis = null;
        try {
            jedis = JedisPoolManager.getInstance().getResource();
            jedis.hset(tableName, key.toString(), value.toString());
        } finally {
            jedis.close();
        }
    }

    public static AuthorizeInfo getString() {
        AuthorizeInfo authorizeInfo = new AuthorizeInfo();
        Jedis jedis = null;
        try {
            jedis = JedisPoolManager.getInstance().getResource();
            String value = jedis.hget("string", "callerId|callerAuthKey");
            authorizeInfo.setIp(value.split("\\|")[0]);
            authorizeInfo.setOther1(value.split("\\|")[1]);
            authorizeInfo.setOther2(value.split("\\|")[2]);
            authorizeInfo.setOther3(value.split("\\|")[3]);
            authorizeInfo.setOther4(value.split("\\|")[4]);
            authorizeInfo.setOther5(value.split("\\|")[5]);
            authorizeInfo.setOther6(value.split("\\|")[6]);
            authorizeInfo.setOther7(value.split("\\|")[7]);
            authorizeInfo.setOther8(value.split("\\|")[8]);
            authorizeInfo.setOther9(value.split("\\|")[9]);
        } finally {
            jedis.close();
        }
        return authorizeInfo;
    }
}
