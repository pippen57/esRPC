package top.pippen.transport;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.pippen.Constants;
import top.pippen.protocol.Message;
import top.pippen.protocol.Request;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Pippen
 * @created 2020/11/04 22:51
 */
public class EsRpcServerHandler extends SimpleChannelInboundHandler<Message<Request>> {

    static Executor executor = Executors.newCachedThreadPool();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message<Request> msg) throws Exception {
        byte extraInfo = msg.getHeader().getExtraInfo();
        if (Constants.isHeartBeat(extraInfo)){
            ctx.writeAndFlush(msg);
            return;
        }
        executor.execute(new InvokeRunnable(msg,ctx));
    }
}
