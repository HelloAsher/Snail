package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置管理组件
 * 该组件用于获取在项目配置文件中的属性
 * Created by yzbfl on 2017-05-18.
 */
public class ConfigurationManager {
    private static Properties prop = new Properties();
    static {
        try {
            InputStream in = ConfigurationManager.class.getClassLoader().getResourceAsStream("UA.properties");
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperties(String key){
        return prop.getProperty(key);
    }

    public static Integer getInteger(String key){
        String value = prop.getProperty(key);
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Boolean getBoolean(String key){
        String value = prop.getProperty(key);
        try {
            return Boolean.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Long getLong(String taskType) {
        String value = prop.getProperty(taskType);
        try {
            return Long.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }
}
