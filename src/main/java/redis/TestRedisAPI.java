package redis;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import utils.JedisPoolManager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SCAL on 2017/7/18.
 */
public class TestRedisAPI {
    public static void main(String[] args){
        JedisPoolManager poolManager = JedisPoolManager.getInstance();
        Jedis jedis = null;
        try {
            jedis = poolManager.getResource();
//            Map<String, String> keyValue = new HashMap<>();
//            keyValue.put("luff", "20");
//            keyValue.put("robin", "20");
//            jedis.hmset("myhash2", keyValue);
//            System.out.println(jedis.del("myhash"));
//            System.out.println(jedis.hget("myhash", "nihao"));
//            String hget = jedis.hget("myhash333", "sjdfkasjd");
//            System.out.println(hget);
//            Long hset = jedis.hset("myhash", "nnnnnn", null);
//            System.out.println(hset);

//
            String hhhh = hhhh();
            System.out.println(hhhh);

//            String set = jedis.set("mymap", "");
////            String set = jedis.get("mymap");
//            System.out.println(set);

        } finally {
            jedis.close();
        }
    }

    public static String hhhh(){
        try {
            InterfaceAccessLog accessLog = new InterfaceAccessLog();
            accessLog.setInterfaceId(11);
            accessLog.setInterfaceName("第七条测试服务");
            accessLog.setCallerCode("89a18dd7-cccc-4b2a-942f-e2387c5b5c74");
            accessLog.setCallerName("川航项目测试");
            accessLog.setCallerAuthKey("6554d83e-50aa-4902-bcde-6e156286cf1f");
            accessLog.setIp("192.168.11.99");
            accessLog.setUrl("http://www.baidu.com");
            accessLog.setParams("params");
            accessLog.setRequestMethod("POST");
            accessLog.setStatus("success");
            accessLog.setStatusCode("8888");
            accessLog.setErrorMsg("no error!");
            accessLog.setErrorCode("0000");
            accessLog.setErrorType("no type");
            accessLog.setRequestTime(new Date());
            Thread.sleep(3000);
            accessLog.setResponseTime(new Date());
            Thread.sleep(3000);
            accessLog.setInterfaceTime(new Date());
            accessLog.setRequestHeader("request header");
            accessLog.setRequestBody("request body");
            accessLog.setResponseHeader("response header");
            accessLog.setResponseBody("response body");
            accessLog.setAuthBeginTime(new Date());
            accessLog.setAuthEndTime(new Date());
            accessLog.setToBeginTime(new Date());
            accessLog.setToEndTime(new Date());
            accessLog.setSumAccessTime(234234.24f);
            accessLog.setSumAuthTime(234.43f);
            accessLog.setSumInsideTime(324f);
            accessLog.setSumInterfaceTime(32f);
            accessLog.setSumLogTime(23f);
            accessLog.setCreateTime(new Date());


            String jsonString = JSON.toJSONString(accessLog);
//            int i = 2/0;
            return jsonString;
        } catch (Exception e){
            System.out.println("sdkjflaskjdfl");
            return "sdfsadf";
        }
    }
}
