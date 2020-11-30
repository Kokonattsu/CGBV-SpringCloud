package cn.tedu.m1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
//简单模式-消费者
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //链接服务器
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.64.140");
        //factory.setHost();
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare("hello rabbit", false, false,false, null);

        //处理消息
        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                //
                byte[] body = delivery.getBody();
                System.out.println("队列内容:"+new String(body));
            }
        };

        //接收消息
        CancelCallback cancelCallback = new CancelCallback() {

            @Override
            public void handle(String s) throws IOException {

            }
        };

        //消费数据
        //参数：队列名，自动确认、处理队列数据的回调函数、取消队列的回调函数
        channel.basicConsume("hello rabbit",true,deliverCallback,cancelCallback);
    }
}
