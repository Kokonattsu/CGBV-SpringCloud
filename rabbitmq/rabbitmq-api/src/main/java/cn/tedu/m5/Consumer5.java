package cn.tedu.m5;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Consumer5 {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.64.140");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        Channel channel = factory.newConnection().createChannel();
        //定义交换机名
        String Switch="topic-logs";
        //定义交换机
        channel.exchangeDeclare(Switch, BuiltinExchangeType.TOPIC);
        //rabbitmq自动配置队列（随机名、非持久、独占、删除、null）.获取队列名
        String queue = channel.queueDeclare().getQueue();
        //队列绑定交换机和绑定的路由键，路由键可有多个
        System.out.println("请输出绑定键，用空格隔开");
        String rKey = new Scanner(System.in).nextLine();
        //以一个或多个空格切割字符串
        String[] rKeys = rKey.split("\\s+");
        for (String r:rKeys){
            channel.queueBind(queue, Switch, r);
        }
        //回调函数
        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                String msg = new String(delivery.getBody());
                //获取路由键
                String routingKey = delivery.getEnvelope().getRoutingKey();
                System.out.println("消息：routingKey:"+routingKey+",msg:"+msg);
            }
        };
        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {

            }
        };
        //消费队列
        //参数：队列名，自动确认、处理队列数据的回调函数、取消队列的回调函数
        channel.basicConsume(queue, true,deliverCallback,cancelCallback);



    }
}
