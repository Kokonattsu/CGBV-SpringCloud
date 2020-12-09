package cn.tedu.order.service;

import cn.tedu.order.entity.Order;
import cn.tedu.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void create(Order order) {
        //TODO:从全局唯一发号器获得id
        Long orderId=Long.valueOf(new Random().nextInt(Integer.MAX_VALUE));
        order.setId(orderId);
        orderMapper.create(order);
        log.info("创建订单:"+order.getId());
        //TODO:减少商品库存
        //TODO:减少账户余额
    }
}
