package Netty.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Created by SCAL on 2017/6/29.
 */
public class Server {
    private Selector selector;
    public Server init(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        return this;
    }

    public void listen() throws IOException {
        System.out.println("server started, listening......");

        while (true){
            selector.select();

            Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
            while (selectionKeyIterator.hasNext()){
                SelectionKey selectionKey = selectionKeyIterator.next();
                selectionKeyIterator.remove();
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.write(ByteBuffer.wrap(new String("i have received you request!").getBytes()));
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    System.out.println("client request......");
                }else if (selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    socketChannel.read(byteBuffer);
                    String message = new String(byteBuffer.array());

                    System.out.println("i have received you massage: " + message);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new Server().init(9981).listen();
    }

}
