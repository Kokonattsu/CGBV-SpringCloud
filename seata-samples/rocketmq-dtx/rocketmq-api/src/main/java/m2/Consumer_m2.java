package m2;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer_m2 {
    public static void main(String[] args) throws Exception {
        //创建消费者
        DefaultMQPushConsumer c = new DefaultMQPushConsumer(
                "consumer-demo2");
        c.setNamesrvAddr("192.168.64.141:9876");
        //"*" 代表消费者订阅Topic4分类下所有的二级分类
        c.subscribe("Topic2", "*");
        //向消息服务器注册顺序消息监听器
        c.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                String t = Thread.currentThread().getName();

                for (MessageExt msg : list) {
                    //当前线程，消息队列的索引，
                    System.out.println(t+" - "+ msg.getQueueId() + " - " +new String(msg.getBody()));
                }

                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        c.start();
        System.out.println("开始消费数据");
    }
}
