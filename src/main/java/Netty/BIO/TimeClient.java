package Netty.BIO;

import java.io.*;
import java.net.Socket;

/**
 * Created by SCAL on 2017/6/27.
 */
public class TimeClient {
    public static void main(String[] args) throws InterruptedException {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            InputStream inputStream = socket.getInputStream();
            printWriter.println("hello!");
            printWriter.flush();
            Thread.sleep(1000);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("received msg from server: " + bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
