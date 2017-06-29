package IO.AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * Created by SCAL on 2017/6/29.
 */
public class Client implements Runnable {
    private AsynchronousSocketChannel asynchronousSocketChannel;

    public Client() throws IOException {
        this.asynchronousSocketChannel = AsynchronousSocketChannel.open();
    }

    public void connect(){
        asynchronousSocketChannel.connect(new InetSocketAddress("127.0.0.1", 9981));
    }

    public void write(String request){

    }

    @Override
    public void run() {

    }
}
