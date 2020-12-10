package cn.tedu.order.tcc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 第二阶段为了处理幂等性问题这里首先添加一个工具类 ResultHolder。
 *
 * 这个工具也可以在第二阶段 Confirm 或 Cancel 阶段对第一阶段的成功与否进行判断，在第一阶段成功时需要保存一个标识。
 *
 * ResultHolder可以为每一个全局事务保存一个标识：
 */
public class ResultHolder {
    private static Map<Class<?>, Map<String, String>> map = new ConcurrentHashMap<Class<?>, Map<String, String>>();

    public static void setResult(Class<?> actionClass, String xid, String v) {
        Map<String, String> results = map.get(actionClass);

        if (results == null) {
            synchronized (map) {
                if (results == null) {
                    results = new ConcurrentHashMap<>();
                    map.put(actionClass, results);
                }
            }
        }

        results.put(xid, v);
    }

    public static String getResult(Class<?> actionClass, String xid) {
        Map<String, String> results = map.get(actionClass);
        if (results != null) {
            return results.get(xid);
        }

        return null;
    }

    public static void removeResult(Class<?> actionClass, String xid) {
        Map<String, String> results = map.get(actionClass);
        if (results != null) {
            results.remove(xid);
        }
    }
}

