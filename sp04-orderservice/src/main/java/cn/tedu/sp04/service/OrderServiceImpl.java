package cn.tedu.sp04.service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.sp04.feign.ItemClient;
import cn.tedu.sp04.feign.UserClient;
import cn.tedu.web.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemClient itemClient;
    @Autowired
    private UserClient userClient;

    @Override
    public Order getOrder(String orderId) {
        //调用user-service获取用户信息
        User user = userClient.getUser(1).getData();
        //调用item-service获取商品信息
        List<Item> items= itemClient.doGetItem(orderId).getData();

        Order order = new Order();
        order.setId(orderId).setUser(user).setItems(items);
        return order;
    }

    @Override
    public void addOrder(Order order) {
        //调用item-service减少商品库存
        itemClient.doDecreaseNumbers(order.getItems());
        //调用user-service增加用户积分
        userClient.addScore(1, 10);
        log.info("保存订单："+order.getId());
    }

}
