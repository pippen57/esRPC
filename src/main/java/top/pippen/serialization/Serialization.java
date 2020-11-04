package top.pippen.serialization;

import java.io.IOException;

/**
 * 序列号API
 *
 * @author Pippen
 * @created 2020/11/04 15:51
 */
public interface Serialization {


    <T> byte[] serialize(T obj)throws IOException;

    <T> T deSerialize(byte[] data, Class<T> clz)throws IOException;
}
