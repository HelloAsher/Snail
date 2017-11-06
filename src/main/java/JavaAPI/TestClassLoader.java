package JavaAPI;

import sun.misc.Launcher;

import java.net.URL;
import java.sql.SQLException;

public class TestClassLoader {
    public static void main(String[] args){
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("这是bootstrapClassLoader加载的东西：");
        for(URL url: urLs){
            System.out.println(url);
        }
        try {
            System.out.println("\n这是EXTClassLoader加载的东西：\n" + System.getProperty("java.ext.dirs") + "\n");
            System.out.println("这是APPClassLoader加载的东西：\n" + System.getProperty("java.class.path") + "\n");
            throw new SQLException("");
        }catch (RuntimeException|SQLException e){
            e.printStackTrace();
        }
    }
}
