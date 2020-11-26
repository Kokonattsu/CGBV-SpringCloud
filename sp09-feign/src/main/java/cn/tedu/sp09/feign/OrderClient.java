package cn.tedu.sp09.feign;

import cn.tedu.sp01.pojo.Order;
import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "order-service")
public interface OrderClient {
    @GetMapping("/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId);

    @GetMapping("/")
    public JsonResult addOrder();
}
