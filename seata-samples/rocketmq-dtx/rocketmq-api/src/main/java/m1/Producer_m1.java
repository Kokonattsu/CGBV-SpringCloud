package m1;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import java.util.Scanner;

/**
 * 发送同步消息
 */
public class Producer_m1 {
    public static void main(String[] args) throws Exception{
        //创建默认生产者，指定加入的生产者组
        DefaultMQProducer p = new DefaultMQProducer(
                "producer-demo1");
        //指定注册中心
        p.setNamesrvAddr("192.168.64.141:9876");
        //启动生产者
        p.start();


        //向消息服务器发送消息
        //参数:一级分类, 二级分类, 消息内容
        String topic = "Topic1";
        String tag = "TagA";
//        String msgstr= LocalDateTime.now().toString();
//        Message msg = new Message(topic, tag, msgstr.getBytes());
//        //发送,并得到消费服务的反馈
//        SendResult sendResult = p.send(msg);
//        System.out.println(sendResult);
//        //关闭服务
//        p.shutdown();

        while (true) {
            System.out.print("输入消息,用逗号分隔多条消息: ");
            String[] a = new Scanner(System.in).nextLine().split(",");

            for (String s : a) {
                Message msg = new Message(topic, tag, s.getBytes()); //一级分类, 二级分类, 消息内容
                SendResult r = p.send(msg);// 发送消息后会得到服务器反馈, 包含: smsgId, sendStatus, queue, queueOffset, offsetMsgId
                System.out.println(r);
            }
        }

    }
}
