package IO.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by SCAL on 2017/6/29.
 */
public class Client {
    private Selector selector;

    public Client init(String serverIP, int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(serverIP, port));
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        return this;
    }

    public void listen() throws IOException {
        System.out.println("client started!");

        while (true){
            selector.select();
            Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
            while (selectionKeyIterator.hasNext()){
                SelectionKey selectionKey = selectionKeyIterator.next();

                if (selectionKey.isConnectable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    if (socketChannel.isConnectionPending()){
                        socketChannel.finishConnect();
                    }

                    socketChannel.configureBlocking(false);

                    socketChannel.write(ByteBuffer.wrap(new String("i request a item!").getBytes()));
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    System.out.println("have send a massage to server!");
                }else if (selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    socketChannel.read(byteBuffer);
                    String massage = new String(byteBuffer.array());

                    System.out.println("i have received a massage from server: " + massage);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Client().init("127.0.0.1", 9981).listen();
    }
}
