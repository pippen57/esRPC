package top.pippen.transport;

import java.lang.reflect.Method;


import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import top.pippen.factory.BeanManager;
import top.pippen.protocol.Header;
import top.pippen.protocol.Message;
import top.pippen.protocol.Request;
import top.pippen.protocol.Response;

/**
 * @author pippen
 */
@Slf4j
public class InvokeRunnable implements Runnable {

    private final ChannelHandlerContext ctx;
    private final Message<Request> message;

    public InvokeRunnable(Message<Request> message, ChannelHandlerContext ctx) {
        this.message = message;
        this.ctx = ctx;
    }

    @Override
    public void run() {
        Response response = new Response();
        Object result = null;
        try {
            Request request = message.getContent();
            String serviceName = request.getServiceName();
            // 这里提供BeanManager对所有业务Bean进行管理
            Object bean = BeanManager.getBean(serviceName);
            // 下面通过反射调用Bean中的相应方法
            Method method = bean.getClass().getMethod(request.getMethodName(), request.getArgTypes());
            result = method.invoke(bean, request.getArgs());
        } catch (Exception e) {
            // 省略异常处理
            e.printStackTrace();
        }
        Header header = message.getHeader();
        header.setExtraInfo((byte) 1);
        // 设置响应结果
        response.setResult(result);
        // 将响应消息返回给客户端
        ctx.writeAndFlush(new Message<>(header, response));
    }

}
