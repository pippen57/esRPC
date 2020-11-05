package top.pippen.transport;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.pippen.Constants;
import top.pippen.protocol.Message;
import top.pippen.protocol.Response;

/**
 * @author Pippen
 * @created 2020/11/04 23:18
 */
public class EsRpcClientHandler extends SimpleChannelInboundHandler<Message<Response>> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message<Response> msg) throws Exception {
        NettyResponseFuture responseFuture =
                Connection.IN_FLIGHT_REQUEST_MAP
                        .remove(msg.getHeader().getMessageId());
        Response response = msg.getContent();
        // 心跳消息特殊处理
        if (response == null && Constants.isHeartBeat(
                msg.getHeader().getExtraInfo())) {
            response = new Response();
            response.setCode(Constants.HEARTBEAT_CODE);
        }
        responseFuture.getPromise().setSuccess(response);
    }
}
