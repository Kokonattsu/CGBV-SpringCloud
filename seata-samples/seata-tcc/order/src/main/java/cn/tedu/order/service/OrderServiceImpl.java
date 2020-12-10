package cn.tedu.order.service;

import cn.tedu.order.entity.Order;
import cn.tedu.order.feign.AccountClient;
import cn.tedu.order.feign.EasyIdGeneratorClient;
import cn.tedu.order.feign.StorageClient;
import cn.tedu.order.mapper.OrderMapper;
//import io.seata.spring.annotation.GlobalTransactional;
import cn.tedu.order.tcc.OrderTccAction;
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
    private OrderTccAction orderTccAction;

    //开启全局事务
//    @GlobalTransactional
    @Override
    public void create(Order order) {
        //从全局唯一发号器获得id   order_business
        Long orderId =Long.valueOf(easyIdGeneratorClient.
                nextId("order_business"));
        order.setId(orderId);
        //--------TCC事务创建订单-------
        orderTccAction.preparCreateOrder(
                //Seata框架的拦截器会自动创建上下文对象
                null,
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                order.getCount(),
                order.getMoney());




//        //减少商品库存
//        storageClient.decrease(
//                order.getProductId(),
//                order.getCount());
//        //减少账户余额
//        accountClient.decrease(
//                order.getUserId(),
//                order.getMoney());
        log.info("订单:"+orderId+"创建完毕");
    }
}
