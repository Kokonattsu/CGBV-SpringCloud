package com.pd;

import com.pd.pojo.PdOrder;
import com.pd.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//定义接收消息的队列
@RabbitListener(queues = "orderQueue")
public class OrderConsumer {
    //消费者代码由消息触发执行

    @Autowired
    private OrderService orderService;
    //处理所接收消息的回调函数，只能定义一个
    @RabbitHandler
    public void receive(PdOrder pdOrder) throws Exception {
        orderService.saveOrder(pdOrder);
        System.out.println("----------------订单已储存------------------");
    }
}
