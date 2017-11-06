package RPC.socketrpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class RPCServer {
    ConcurrentHashMap<String, Object> classMap = new ConcurrentHashMap<>();

    public static void invoker(int port) throws Exception{
        ServerSocket server = new ServerSocket(port);
        while (true){
            final Socket socket = server.accept();
            new Thread(new Runnable() {
                ObjectOutputStream out = null;
                @Override
                public void run() {
                    try {
                        out = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        String className = input.readUTF();
                        String methodName = input.readUTF();
                        Class<?> parameterTypes = (Class<?>) input.readObject();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
