package cn.tedu.m3;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

//发布-订阅模式：生产者
public class Producer3 {
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
        while (true){
            System.out.print("输入消息：");
            String msg = new Scanner(System.in).nextLine();
            //此模式下定义队列是无效的
            channel.basicPublish(Switch, "", null, msg.getBytes());
        }


    }
}
