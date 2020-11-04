package top.pippen.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.pippen.Constants;
import top.pippen.compress.Compressor;
import top.pippen.compress.CompressorFactory;
import top.pippen.protocol.Header;
import top.pippen.protocol.Message;
import top.pippen.serialization.Serialization;
import top.pippen.serialization.SerializationFactory;

/**
 * 自定义消息编码
 *
 * @author Pippen
 * @created 2020/11/04 16:51
 */
public class EsRpcEncoder extends MessageToByteEncoder<Message<Object>> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message<Object> msg, ByteBuf out) throws Exception {
        Header header = msg.getHeader();
        out.writeShort(header.getMagic());
        out.writeByte(header.getVersion());
        out.writeByte(header.getExtraInfo());
        out.writeLong(header.getMessageId());
        Object content = msg.getContent();
        if (Constants.isHeartBeat(header.getExtraInfo())) {
            // 心跳消息，没有消息体，这里写入0
            out.writeInt(0);
            return;
        }
        // 按照extraInfo部分指定的序列化方式和压缩方式进行处理
        Serialization serialization = SerializationFactory.get(header.getExtraInfo());
        Compressor compressor = CompressorFactory.get(header.getExtraInfo());
        byte[] payload = compressor.compress(serialization.serialize(content));
        // 写入消息体长度
        out.writeInt(payload.length);
        // 写入消息体
        out.writeBytes(payload);
    }
}
