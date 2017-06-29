package spark.sparkstreaming.driver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * Created by Asher on 2017/1/12.
 */
public class SocketDriver extends AbstractDriver {
    private static final Logger LOG = LogManager.getLogger(SocketDriver.class);
    private String hostname;
    private int port;
    private SocketStream socketStream;


    public SocketDriver(String path, String hostname, int port) {
        super(path);
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public void init() throws Exception {
        socketStream = new SocketStream(hostname, port);
        SocketChannel socketChannel = socketStream.init();
        socketStream.kickOff(socketChannel);
        socketStream.start();
    }

    @Override
    public void close() throws Exception {
        socketStream.done();
        if (socketStream != null){
            socketStream.close();
        }
    }

    @Override
    public void sendRecord(String record) throws Exception {
        socketStream.sendMsg(record + "\\n");
    }

    class SocketStream extends Thread{
        private String hostname;
        private int port;
        private volatile boolean isDone = false;

        private ServerSocketChannel server;
        private SocketChannel socket = null;
        private long totalBytes;
        private long totalLines;

        public SocketStream(String hostname, int port){
            this.hostname = hostname;
            this.port = port;
            totalBytes = 0;
            totalLines = 0;
        }

        public SocketChannel init() throws Exception{
            server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(hostname, port));
            LOG.info(String.format("Listening on %s", server.getLocalAddress()));
            return server.accept();
        }

        public void kickOff(SocketChannel socket){
            LOG.info(String.format("Kicking off data transfer"));
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                while (!isDone){
                    Thread.sleep(1000);
                }
            } catch (Exception e){
                LOG.error(e.getMessage());
            }
        }

        public void sendMsg(String msg) throws IOException {
            if (socket != null){
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
                int i = socket.write(buffer);
                totalBytes += i;
            }else {
                throw new IOException("Client has not connected!");
            }
            totalLines++;
        }

        public void done(){
            isDone = true;
        }

        public void close() throws IOException {
            if(socket != null){
                socket.close();
                socket = null;
                LOG.info(String.format("SocketStream is closing after writing %d bytes " +
                        "and %d lines", totalBytes, totalLines));
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        if (args.length != 3) {
//            System.err.println("Usage: SocketDriver <path_to_input_folder> <hostname> <port>");
//            System.exit(1);
//        }
        String path = String.format("D:\\QQ消息\\datas");
        String hostname = "localhost";
        int port = 50070;

        SocketDriver socketDriver = new SocketDriver(path, hostname, port);
        try {
            socketDriver.execute();
        } finally {
            socketDriver.close();
        }

    }
}
