package top.pippen.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author pippen
 */
public class BeanManager {

    private static final Map<String, Object> SERVICES = new ConcurrentHashMap<>();

    public static void registerBean(String serviceName, Object bean) {
        SERVICES.put(serviceName, bean);
    }

    public static Object getBean(String serviceName) {
        return SERVICES.get(serviceName);
    }
}