package cn.tedu.order.service;

import cn.tedu.order.entity.Order;
import cn.tedu.order.feign.AccountClient;
import cn.tedu.order.feign.EasyIdGeneratorClient;
import cn.tedu.order.feign.StorageClient;
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
    @Autowired
    private EasyIdGeneratorClient easyIdGeneratorClient;
    @Autowired
    private StorageClient storageClient;
    @Autowired
    private AccountClient accountClient;

    @Override
    public void create(Order order) {
        //从全局唯一发号器获得id   order_business
        Long orderId =Long.valueOf(easyIdGeneratorClient.
                nextId("order_business"));
        //Long orderId=Math.abs(new Random().nextLong());
        order.setId(orderId);
        orderMapper.create(order);
        //减少商品库存
        storageClient.decrease(
                order.getProductId(),
                order.getCount());
        //减少账户余额
        accountClient.decrease(
                order.getUserId(),
                order.getMoney());
        log.info("订单:"+orderId+"创建完毕");
    }
}
