package redis;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import redis.clients.jedis.Jedis;

public class TestSerialize {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        serialize();
        long endTime = System.nanoTime();
        System.out.println((float) (endTime - startTime) / 1000000 + "ms");

        long startTime2 = System.nanoTime();
        AuthorizeInfo authorizeInfo = deSerialize();
        long endTime2 = System.nanoTime();
        System.out.println((float) (endTime2 - startTime2) / 1000000 + "ms");
        System.out.println(authorizeInfo);
    }

    private static void serialize(){
        String tableName = "protostuff";

        StringBuilder key = new StringBuilder();
        key.append("callerId");
        key.append("|callerAuthKey");

        Schema<AuthorizeInfo> schema = RuntimeSchema.getSchema(AuthorizeInfo.class);
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

        byte[] byteArray = ProtostuffIOUtil.toByteArray(authorizeInfo, schema, LinkedBuffer.allocate(1024));

        Jedis jedis = null;
        try {
            jedis = JedisPoolManager.getInstance().getResource();
            jedis.hset(tableName.getBytes(), key.toString().getBytes(), byteArray);
        }finally {
            jedis.close();
        }
    }

    private static AuthorizeInfo deSerialize(){
        Schema<AuthorizeInfo> schema = RuntimeSchema.getSchema(AuthorizeInfo.class);
        AuthorizeInfo authorizeInfo = schema.newMessage();
        Jedis jedis = null;
        try {
            jedis = JedisPoolManager.getInstance().getResource();
            byte[] bytes = jedis.hget("protostuff".getBytes(), "callerId|callerAuthKey".getBytes());
            ProtostuffIOUtil.mergeFrom(bytes, authorizeInfo, schema);
        }finally {
            jedis.close();
        }
        return authorizeInfo;
    }
}
