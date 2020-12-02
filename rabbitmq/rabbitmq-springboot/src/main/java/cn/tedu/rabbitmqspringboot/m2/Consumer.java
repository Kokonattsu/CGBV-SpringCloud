package cn.tedu.rabbitmqspringboot.m2;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @RabbitListener(queues = "task-rabbit")
    public void receive1(String msg){
        System.out.println("消费者1收到消息："+msg);
    }
    @RabbitListener(queues = "task-rabbit")
    public void receive2(String msg){
        System.out.println("消费者2收到消息："+msg);
    }
}
