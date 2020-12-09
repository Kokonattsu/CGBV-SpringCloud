package cn.tedu.order.controller;

import cn.tedu.order.entity.Order;
import cn.tedu.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    //http://localhost:8083/create?userId=1&productId=1&count=10&money=100
    @RequestMapping("/create")
    public String create(Order order){
        orderService.create(order);
        return "订单创建完成";
    }
}
