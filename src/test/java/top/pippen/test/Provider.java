package top.pippen.test;

import org.apache.curator.x.discovery.ServiceInstance;
import top.pippen.factory.BeanManager;
import top.pippen.registry.ServerInfo;
import top.pippen.registry.ZookeeperRegistry;
import top.pippen.transport.EsRpcServer;

/**
 * @author Pippen
 * @created 2020/11/06 20:34
 */
public class Provider {
    public static void main(String[] args) throws Exception {
        BeanManager.registerBean("esService", new EsServiceImpl());
        ZookeeperRegistry<ServerInfo> discovery =
                new ZookeeperRegistry<>();
        discovery.start();
        ServerInfo serverInfo = new ServerInfo("127.0.0.1", 20880);
        discovery.registerService(ServiceInstance.<ServerInfo>builder().name("esService").payload(serverInfo).build());
        // 启动DemoRpcServer，等待Client的请求
        EsRpcServer rpcServer = new EsRpcServer(20880);
        rpcServer.start();
        Thread.sleep(10000000L);
    }
}
