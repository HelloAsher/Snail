package Netty.BIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by SCAL on 2017/6/9.
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            while (true){
                System.out.println("the time server started at port 8080....");
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                System.out.println("received message from client at 8080: " + bufferedReader.readLine());
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.write("i have received you msg!");
                printWriter.flush();
                bufferedReader.close();
                printWriter.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket != null){
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
