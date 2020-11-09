package top.pippen.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 消息
 * @author Pippen
 * @created 2020/11/04 15:41
 */
@Data
@AllArgsConstructor
public class Message<T> implements Serializable {
    private Header header;

    private T content;


}
