package RPC.socketrpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class RPCProxy {
    public static <T> T create(final Object target){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket("localhost", 8080);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                try{
                    outputStream.writeUTF(target.getClass().getName());
                    outputStream.writeUTF(method.getName());
                    outputStream.writeObject(method.getParameterTypes());
                    outputStream.writeObject(args);
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    Object result = inputStream.readObject();
                    try {
                        if(result instanceof Throwable){
                            throw (Throwable) result;
                        }
                        return result;
                    } finally {
                        inputStream.close();
                    }
                }finally {
                 outputStream.close();
                 socket.close();
                }
            }
        });
    }
}
