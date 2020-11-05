package top.pippen.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *                                          16 个字节
 * ----------------------------------------------------------------------------------------------------------------------
 *  short  |  byte     |                  byte                                     |      long     |   int     |   byte
 *  magic  |  version  |                extraInfo                                  |    messageId  |   size    |   body
 *                                0            1-2      3-4        5-6             |               |           |
 *  魔数    |  协议版本  |  消息类型(请求OR响应)  序列化方式  压缩方式  请求类型(正常,心跳)    |     消息ID     |  消息体长度 |  消息体
 * ----------------------------------------------------------------------------------------------------------------------
 *
 * 消息头
 * @author Pippen
 * @created 2020/11/04 15:24
 */
@Data
@AllArgsConstructor
public class Header {

    /**
     * 魔数
     */
    private short magic;
    /**
     * 协议版本
     */
    private byte version;
    /**
     * 附加信息
     */
    private byte extraInfo;
    /**
     * 消息ID
     */
    private Long messageId;
    /**
     * 消息体长度
     */
    private Integer size;

    public Header(short magic, byte version) {
        this.magic = magic;
        this.version = version;
    }
}
