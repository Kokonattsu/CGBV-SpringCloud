package cn.tedu.rabbitmqspringboot.m4;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Producer4 {
    @Autowired
    AmqpTemplate t;

    public void send() {
        while (true) {
            System.out.print("输入消息:");
            String s = new Scanner(System.in).nextLine();
            System.out.print("输入路由键:");
            String key = new Scanner(System.in).nextLine();
            // 第二个参数指定路由键
            t.convertAndSend("direct_logs",key,s);
        }
    }
}
