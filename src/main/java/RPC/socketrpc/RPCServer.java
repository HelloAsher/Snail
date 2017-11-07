package RPC.socketrpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class RPCServer {
    public static ConcurrentHashMap<String, Object> classMap = new ConcurrentHashMap<>();

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
                        Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                        Object[] arguments = (Object[]) input.readObject();
                        Object claszz;
                        if(!classMap.containsKey(className)){
                            claszz = Class.forName(className).newInstance();
                            classMap.put(className, claszz);
                        }else {
                            claszz = classMap.get(className);
                        }
                        Method method = claszz.getClass().getMethod(methodName, parameterTypes);
                        Object result = method.invoke(claszz, arguments);
                        out.writeObject(result);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args) throws Exception {
        invoker(8080);
    }
}
