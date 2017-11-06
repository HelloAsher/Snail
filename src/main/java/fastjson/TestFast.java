package fastjson;

import com.alibaba.fastjson.JSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestFast {
    public static void main(String[] args){
        MyObject myObject = new MyObject();
        String jsonString = String.format("{\"secID\": \"002860.XSHE\", \"ticker\": \"002860\", \"secShortName\": \"星帅尔\", \"cnSpell\": \"XSE\", \"exchangeCD\": \"XSHE\", \"assetClass\": \"E\", \"listStatusCD\": \"L\", \"listDate\": \"2017-04-12\", \"transCurrCD\": \"CNY\", \"partyID\": 66202}");
        System.out.println(jsonString);
        myObject.setMyJson(jsonString);
        myObject.setOtherString("testJson");
        String jsonString1 = JSON.toJSONString(myObject);
        System.out.println(myObject);
        System.out.println(jsonString1);

        MyObject myObject1 = JSON.parseObject(jsonString1, MyObject.class);
        System.out.println(myObject1);

        JSONObject jObject = new JSONObject(jsonString);
        Object sdfas = jObject.get("sdfas");

    }
}
