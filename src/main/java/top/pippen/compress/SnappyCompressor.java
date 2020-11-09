package top.pippen.compress;


import java.io.IOException;

import org.xerial.snappy.Snappy;

/**
 *  Snappy 消息压缩实现
 * @author pippen
 */
public class SnappyCompressor implements Compressor {

    @Override
    public byte[] compress(byte[] array) throws IOException {
        if (array == null) {
            return null;
        }
        return Snappy.compress(array);
    }
    @Override
    public byte[] unCompress(byte[] array) throws IOException {
        if (array == null) {
            return null;
        }
        return Snappy.uncompress(array);
    }

    public static void main(String[] args) throws IOException {
        SnappyCompressor snappyCompressor = new SnappyCompressor();
        byte[] compress = snappyCompressor.compress("pippen".getBytes());
        System.out.println(compress);
    }
}
