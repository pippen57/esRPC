package top.pippen.compress;


/**
 * 消息体压缩工厂
 * @author pippen
 */
public class CompressorFactory {
    public static Compressor get(byte extraInfo) {
        switch (extraInfo & 24) {
            case 0x0:
                return new SnappyCompressor();
            default:
                return new SnappyCompressor();
        }
    }
}
