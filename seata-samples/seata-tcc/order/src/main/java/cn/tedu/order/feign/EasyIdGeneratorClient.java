package cn.tedu.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//发号器的客户端接口
@FeignClient(name = "easy-id-generator")
public interface EasyIdGeneratorClient {
    //http://localhost:9090/segment/ids/next_id?businessType=order_business
    @GetMapping("/segment/ids/next_id")
    String nextId(@RequestParam String businessType);
}
