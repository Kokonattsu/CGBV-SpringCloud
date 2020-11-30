package cn.tedu.m4;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer4 {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.64.140");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        Channel channel = factory.newConnection().createChannel();
        //定义交换机名
        String Switch="direct-logs";
        //定义交换机
        channel.exchangeDeclare(Switch, BuiltinExchangeType.DIRECT);
        while (true){
            System.out.println("输入消息：");
            String msg = new Scanner(System.in).nextLine();
            System.out.println("输入路由键：");
            String rkey = new Scanner(System.in).nextLine();
            channel.basicPublish(Switch,rkey, null,msg.getBytes() );
        }
    }
}
