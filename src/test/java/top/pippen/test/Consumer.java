package top.pippen.test;

import top.pippen.proxy.EsRpcProxy;
import top.pippen.registry.ServerInfo;
import top.pippen.registry.ZookeeperRegistry;

/**
 * @author Pippen
 * @created 2020/11/06 20:34
 */
public class Consumer {
    public static void main(String[] args) throws Exception{
        // 创建ZookeeperRegistr对象
        ZookeeperRegistry<ServerInfo> discovery = new ZookeeperRegistry<>();
        discovery.start();
        // 创建代理对象，通过代理调用远端Server
        EsService esService = EsRpcProxy.newInstance(EsService.class, discovery);
        // 调用sayHello()方法，并输出结果
        int result = esService.sayHello("pippen");
        System.out.println(result);
        Thread.sleep(10000000L);
    }
}
