package top.pippen.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import top.pippen.Constants;
import top.pippen.compress.Compressor;
import top.pippen.compress.CompressorFactory;
import top.pippen.protocol.Header;
import top.pippen.protocol.Message;
import top.pippen.protocol.Request;
import top.pippen.protocol.Response;
import top.pippen.serialization.Serialization;
import top.pippen.serialization.SerializationFactory;

import java.util.List;

/**
 * 自定义消息解码
 *
 * @author Pippen
 * @created 2020/11/04 16:28
 */
public class EsRpcDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < Constants.HEADER_SIZE) {
            return;
        }
        // 记录readIndex指针位置,方便重置
        in.markReaderIndex();
        // 获取魔数
        short magic = in.readShort();
        if (magic != Constants.MAGIC) {
            // 重置readIndex 指针
            in.resetReaderIndex();
            throw new RuntimeException("magic number error: " + magic);
        }

        // 依次读取消息版本、附加信息、消息 ID 以及消息体长度
        byte version = in.readByte();
        byte extraInfo = in.readByte();
        long messageId = in.readLong();
        int size = in.readInt();
        Object request = null;
        // 心跳消息是没有消息体的，无须读取
        if (!Constants.isHeartBeat(extraInfo)) {
            // 对于非心跳消息，没有积累到足够的数据是无法进行反序列化的
            if (in.readableBytes() < size) {
                in.resetReaderIndex();
                return;
            }
            // 读取消息体并进行反序列化
            byte[] payload = new byte[size];
            in.readBytes(payload);
            // 这里根据消息头中的extraInfo部分选择相应的序列化和压缩方式
            Serialization serialization =
                    SerializationFactory.get(extraInfo);
            Compressor compressor = CompressorFactory.get(extraInfo);
            // 经过解压缩和反序列化得到消息体
            if (Constants.isRequest(extraInfo)) {
                request = serialization.deSerialize(
                        compressor.unCompress(payload), Request.class);
            }else {
                request = serialization.deSerialize(
                        compressor.unCompress(payload), Response.class);
            }


        }
        Header header = new Header(magic, version, extraInfo, messageId, size);
        Message<Object> message = new Message<>(header, request);
        out.add(message);
    }
}
