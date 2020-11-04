package top.pippen.compress;

import java.io.IOException;

/**
 * 消息体压缩
 *
 * @author Pippen
 * @created 2020/11/04 16:07
 */
public interface Compressor {
    byte[] compress(byte[] array) throws IOException;
    byte[] unCompress(byte[] array) throws IOException;
}
