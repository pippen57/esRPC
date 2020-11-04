package top.pippen.protocol;

import lombok.Data;

/**
 * 请求
 * @author Pippen
 * @created 2020/11/04 15:43
 */
@Data
public class Request {

    /**
     * 请求的Server类名
     */
    private String serverName;

    /**
     * 请求的方法名
     */
    private String methodName;
    /**
     * 请求方法的参数类型
     */
    private Class[] argTypes;

    /**
     * 请求方法
     */
    private Object[] args;
}
