package cn.tedu.m2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

//工作模式-生产者
public class producer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.print("输入消息：");
        //建立连接
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.64.140");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //创建队列
        //队列的参数：是否是持久队列、是否排他独占、是否自动删除、其他参数
        channel.queueDeclare("rabbit",false,false,false,null);
        //已经存在的队列，属性是不可变的，（比如持久变非持久，会报错）
        channel.queueDeclare("rabbitdb",true,false,false,null);
        //发送队列

        while (true){
            String msg = new Scanner(System.in).nextLine();
            //channel.basicPublish("", "rabbit",null, msg.getBytes());
            //发送持久队列
            //参数：默认交换机、队列名、其他消息属性配置、消息的内容
            channel.basicPublish("", "rabbit", MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());

        }
    }
}
