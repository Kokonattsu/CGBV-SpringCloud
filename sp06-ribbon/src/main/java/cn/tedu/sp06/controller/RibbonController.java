package cn.tedu.sp06.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 使用RestTemplate远程调用item-service
     * url：http://localhost:8001/{orderId}
     */
    @HystrixCommand(fallbackMethod="getItemsDF")
    @GetMapping("/item-service/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId){
        return restTemplate.getForObject("http://item-service/{1}", JsonResult.class,orderId);

    }

    //http://localhost:8001/deleteItem/
    //访问http://localhost:3001/deleteItem/
    @HystrixCommand(fallbackMethod="doDecreaseNumbersDF")
    @PostMapping("item-service/deleteItem")
    public JsonResult doDecreaseNumbers(@RequestBody List<Item> itemList){
        return restTemplate.postForObject(
                "http://item-service/deleteItem",
                itemList,
                JsonResult.class );
    }

    /**
     * 远程调用user-service
     * url：
     *  Get   http://localhost:8101/7
     *  Get   http://localhost:8101/7/score?score=123
     */
    @HystrixCommand(fallbackMethod="getUserDF")
    @GetMapping("user-service/{userId}")
    public JsonResult<User> getUser(@PathVariable Integer userId){
        return restTemplate.getForObject(
                "http://user-service/{1}",
                JsonResult.class,
                userId);
    }
    @HystrixCommand(fallbackMethod="addSoreDF")
    @GetMapping("user-service/{userId}/score")
    public JsonResult   addSore(@PathVariable Integer userId,Integer score){
        return restTemplate.getForObject(
                "http://user-service/{1}/score?score={2}",
                JsonResult.class,
                userId,score);
    }

    /**
     * 远程调用order-service
     * get http://localhost:8201/1
     * get http://localhost:8201/
     */
    @HystrixCommand(fallbackMethod="getOrderDF")
    @GetMapping("order-service/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId){
        return restTemplate.getForObject(
                "http://order-service/{1}",
                JsonResult.class,
                orderId);
    }
    @HystrixCommand(fallbackMethod="addOrderDF")
    @GetMapping("order-service/")
    public JsonResult<Order> addOrder(){
        return restTemplate.getForObject(
                "http://order-service",
                JsonResult.class);
    }

    //--------------降级方法-------------
    public JsonResult<List<Item>> getItemsDF(@PathVariable String orderId){
        return JsonResult.err().msg("远程获取订单失败");
    }
    public JsonResult doDecreaseNumbersDF(@RequestBody List<Item> itemList){
        return JsonResult.err().msg("远程减少库存失败");
    }

    public JsonResult<User> getUserDF(@PathVariable Integer userId){
        return JsonResult.err().msg("远程获取用户失败");
    }

    public JsonResult   addSoreDF(@PathVariable Integer userId,Integer score){
        return JsonResult.err().msg("远程添加用户积分失败");
    }

    public JsonResult<Order> getOrderDF(@PathVariable String orderId){
        return JsonResult.err().msg("远程获取订单失败");
    }
    public JsonResult<Order> addOrderDF(){
        return JsonResult.err().msg("远程添加订单失败");
    }

}
