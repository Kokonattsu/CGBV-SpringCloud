package m3;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer_m3 {
    public static void main(String[] args) throws MQClientException {
        //创建消费者
        DefaultMQPushConsumer c = new DefaultMQPushConsumer(
                "consumer-demo3");
        c.setNamesrvAddr("192.168.64.141:9876");
        //"*" 代表消费者订阅Topic4分类下所有的二级分类
        c.subscribe("Topic3", "*");
        //向消息服务器注册顺序消息监听器
//        c.registerMessageListener(new MessageListenerOrderly() {
//            @Override
//            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
//                String t = Thread.currentThread().getName();
//
//                for (MessageExt msg : list) {
//                    //当前线程，消息队列的索引，
//                    System.out.println(t+" - "+ msg.getQueueId() + " - " +new String(msg.getBody()));
//                }
//
//                return ConsumeOrderlyStatus.SUCCESS;
//            }
//        });
        c.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext ctx) {
                for (MessageExt msg : list) {
                    System.out.println(new String(msg.getBody()) + " - " + msg);
                }
                if (Math.random()<0.5) {
                    System.out.println("消息处理完成");
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                } else {
                    System.out.println("消息处理失败, 要求服务器稍后重试发送消息");
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
        });


        c.start();
        System.out.println("开始消费数据");
    }

}
