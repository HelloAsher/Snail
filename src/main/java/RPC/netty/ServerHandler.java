package RPC.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class ServerHandler extends ChannelHandlerAdapter {
    public static ConcurrentHashMap<String, Object> classMap = new ConcurrentHashMap<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ClassInfo classInfo = (ClassInfo) msg;
        Object claszz;
        if(!classMap.contains(classInfo.getClassName())){
            claszz = Class.forName(classInfo.getClassName()).newInstance();
            classMap.put(classInfo.getClassName(), claszz);
        }else {
            claszz = classMap.get(classInfo.getClassName());
        }
        Method method = claszz.getClass().getMethod(classInfo.getMethodName(), classInfo.getTypes());
        Object invoke = method.invoke(claszz, classInfo.getObjects());
        ctx.writeAndFlush(invoke);
        ctx.close();

    }
}
