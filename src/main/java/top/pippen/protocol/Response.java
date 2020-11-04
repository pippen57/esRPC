package top.pippen.protocol;

import lombok.Data;

/**
 * 响应
 * @author Pippen
 * @created 2020/11/04 15:46
 */
@Data
public class Response {

    /**
     * 响应错误码,正常响应为0,非0 标识异常响应
     */
    private int code = 0;

    /**
     * 异常信息
     */
    private String errMsg;

    /**
     * 响应消息
     */
    private Object result;

}
