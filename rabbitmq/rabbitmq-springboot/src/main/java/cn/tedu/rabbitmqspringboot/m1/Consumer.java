package cn.tedu.rabbitmqspringboot.m1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "rabbit")
public class Consumer {
    @RabbitHandler
    public void receive(String msg){
        System.out.println("收到消息："+msg);
    }

}
