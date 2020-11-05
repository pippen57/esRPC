package top.pippen.transport;


import io.netty.channel.Channel;
import io.netty.util.concurrent.Promise;
import lombok.Data;
import top.pippen.protocol.Message;

/**
 * @author pippen
 */
@Data
public class NettyResponseFuture<T> {
    private long createTime;
    private long timeOut;
    private Message<T> request;
    private Channel channel;
    private Promise<T> promise;

    public NettyResponseFuture(long createTime, long timeOut, Message<T> request, Channel channel, Promise<T> promise) {
        this.createTime = createTime;
        this.timeOut = timeOut;
        this.request = request;
        this.channel = channel;
        this.promise = promise;
    }

}
