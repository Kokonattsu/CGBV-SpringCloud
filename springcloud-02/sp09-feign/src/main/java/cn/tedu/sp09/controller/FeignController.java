package cn.tedu.sp09.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp09.feign.ItemClient;
import cn.tedu.sp09.feign.OrderClient;
import cn.tedu.sp09.feign.UserClient;
import cn.tedu.web.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeignController {
    @Autowired
    private ItemClient itemClient;
    @Autowired
    private UserClient userClient;
    @Autowired
    private OrderClient orderClient;

    //+++++++++++++++++++++item-service+++++++++++++++++++++=
    @GetMapping("/item-service/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId){
        return itemClient.doGetItem(orderId);
    }
    @PostMapping("item-service/deleteItem")
    public JsonResult doDecreaseNumbers(@RequestBody List<Item> itemList){
        return itemClient.doDecreaseNumbers(itemList);
    }

    //+++++++++++++++++++++user-service+++++++++++++++++++++++++
    @GetMapping("user-service/{userId}")
    public JsonResult<User> getUser(@PathVariable Integer userId){
        return userClient.getUser(userId);
    }
    @GetMapping("user-service/{userId}/score")
    public JsonResult   addSore(@PathVariable Integer userId,Integer score){
        return userClient.addScore(userId, score);
    }

    //++++++++++++++++++++order-service++++++++++++++++++++++++++
    @GetMapping("order-service/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId){
        return orderClient.getOrder(orderId);
    }
    @GetMapping("order-service/")
    public JsonResult<Order> addOrder(){
        return orderClient.addOrder();
    }


}
