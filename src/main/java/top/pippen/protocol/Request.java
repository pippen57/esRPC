package top.pippen.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求
 *
 * @author Pippen
 * @created 2020/11/04 15:43
 */
@Data
public class Request implements Serializable {

    /**
     * 请求的Server类名
     */
    private String serviceName;

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

    public Request(String serviceName, String methodName, Object[] args) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.args = args;
        this.argTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            this.argTypes[i] = args[i].getClass();
        }
    }
}
