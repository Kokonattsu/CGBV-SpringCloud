package cn.tedu.rabbitmqspringboot.m4;


import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer4 {
    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue,
            exchange = @Exchange(name = "direct_logs", declare = "false"),
            key = {"error"}
            ))
    public void receive1(String s) throws Exception {
        System.out.println("receiver1 - 收到: "+s);
    }
    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(name = "direct_logs", declare = "false"),key = {"error","info","warning"}))
    public void receive2(String s) throws Exception {
        System.out.println("receiver2 - 收到: "+s);
    }
}
