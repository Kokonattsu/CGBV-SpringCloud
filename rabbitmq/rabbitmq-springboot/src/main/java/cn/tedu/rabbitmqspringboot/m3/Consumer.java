package cn.tedu.rabbitmqspringboot.m3;


import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @RabbitListener(bindings = @QueueBinding(
            value =@Queue,
            //交换机名、不重新定义交换机
            exchange =@Exchange(name = "logs",declare ="false")
    ))
    public void receive1(String msg){
        System.out.println("消费者一收到消息："+msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value =@Queue,
            //交换机名、不重新定义交换机
            exchange =@Exchange(name = "logs",declare = "false")
    ))
    public void receive2(String msg){
        System.out.println("消费者二收到消息："+msg);
    }

}
