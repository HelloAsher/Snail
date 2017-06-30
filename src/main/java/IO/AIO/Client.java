package IO.AIO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

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
        try {
            asynchronousSocketChannel.write(ByteBuffer.wrap(request.getBytes())).get();
            read();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void read() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            asynchronousSocketChannel.read(byteBuffer).get();
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes);
            System.out.println(new String(bytes, "utf-8"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Client c1 = new Client();
        c1.connect();

        Client c2 = new Client();
        c2.connect();

        Client c3 = new Client();
        c3.connect();

        new Thread(c1, "c1").start();
        new Thread(c2, "c2").start();
        new Thread(c3, "c3").start();

        Thread.sleep(1000);

        c1.write("c1 aaa");
        c2.write("c2 bbbb");
        c3.write("c3 ccccc");
    }
}
