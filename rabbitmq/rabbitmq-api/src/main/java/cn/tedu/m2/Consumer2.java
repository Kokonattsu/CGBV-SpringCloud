package cn.tedu.m2;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//工作模式-消费者
public class Consumer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.64.140");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare("rabbit", false, false,false, null);

        //处理消息
        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                //获取队列信息，打印消息
                byte[] body = delivery.getBody();
                System.out.println("队列内容:"+new String(body));
                for (int i=0;i<body.length;i++){
                    if ('.'==body[i]){
                        try {
                            System.out.println("正在处理");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
                //每次接收信息的条数，处理完成之前不接收消息
                channel.basicQos(1);
                //向服务器发送回执，
                //参数：1、本条消息的回执，2、是否一次返回返回多条回执
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
                System.out.println("消息处理结束");

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
        channel.basicConsume("rabbit",true,deliverCallback,cancelCallback);
    }
}
