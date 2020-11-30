package cn.tedu.m3;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

//发布-订阅模式：消费者
public class Consumer3 {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.64.140");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        Channel channel = factory.newConnection().createChannel();
        //定义交换机
        //参数：交换机名（随意），交换机类型
        String Switch="logs";
        channel.exchangeDeclare(Switch, BuiltinExchangeType.FANOUT);
        //创建队列
        //随机定义队列名
        String queue= UUID.randomUUID().toString();
        //队列名、是否排他、是否持久、是否自动删除、其他参数
        channel.queueDeclare(queue,false,false,true, null);
        //队列绑定交换机，队列名，交换机名，
        channel.queueBind(queue, Switch, "");
        DeliverCallback deliverCallback = new DeliverCallback() {

            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                String msg = new String(delivery.getBody());
                System.out.println("收到消息："+msg);
            }
        };
        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {

            }
        };

        //消费队列：队列名、是否自动回执、处理队列的回调函数、取消队列的回调函数
        channel.basicConsume(queue, true,deliverCallback, cancelCallback);
    }
}
