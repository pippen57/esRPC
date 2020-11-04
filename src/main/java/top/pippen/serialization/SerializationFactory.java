package top.pippen.serialization;

/**
 * 序列号工厂
 * @author pippen
 */
public class SerializationFactory {

    public static Serialization get(byte type) {
        switch (type & 0x7) {
            case 0x0:
                return new HessianSerialization();
            default:
                return new HessianSerialization();
        }

    }
}
