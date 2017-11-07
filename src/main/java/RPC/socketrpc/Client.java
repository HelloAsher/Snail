package RPC.socketrpc;

public class Client {
    public static void main(String[] args) {
        HelloRPC helloRPC = new HelloRPCImpl();
        helloRPC = RPCProxy.create(helloRPC);
        System.err.println(helloRPC.hello("robin"));
    }
}
