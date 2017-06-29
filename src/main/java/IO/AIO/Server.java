package IO.AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by SCAL on 2017/6/29.
 */
public class Server {
    private ExecutorService executorService;
    private AsynchronousChannelGroup threadGroup;
    public AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public Server(int port){
        try {
            executorService = Executors.newCachedThreadPool();
            threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open(threadGroup);

            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("server started at port: " + port);

            asynchronousServerSocketChannel.accept(this, new ServerCompletionHandler());
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Server(9981);
    }
}
