package cn.tedu.m1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
//简单模式-生产者
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //建立连接
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.64.140");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection =factory.newConnection();
        Channel channel = connection.createChannel();
        //让服务器创建队列 （如果已经存在则直接使用）
        //队列的参数：是否是持久队列、是否排他独占、是否自动删除、其他参数
        channel.queueDeclare("hello rabbit",false,false,false,null);
        //消息发送创建的队列
        //参数：默认交换机、队列名、其他消息属性配置、消息的内容
        channel.basicPublish(
                "",
                "hello rabbit",
                null,
                "fack rabbit".getBytes()
        );
        System.out.println("hello rabbit 发送成功！");



        channel.close();
        connection.close();

    }
}
