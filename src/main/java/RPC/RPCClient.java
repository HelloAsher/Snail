//package RPC;
//
//import org.apache.hadoop.conf.Configuration;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//
//public class RPCClient {
//
//	public static void main(String[] args) throws IOException {
//		Barty proxy = RPC.getProxy(Barty.class, 10010,
//				new InetSocketAddress("127.0.0.1", 9527), new Configuration());
//		String sayHi = proxy.sayHi("tomcat");
//		System.out.println(sayHi);
//
//	}
//
//}
