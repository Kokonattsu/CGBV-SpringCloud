package m1;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer_m1 {
    public static void main(String[] args) throws Exception {
        //创建消费者并指定消费者组
        DefaultMQPushConsumer c = new DefaultMQPushConsumer(
                "consumer-demo1");
//        c.setNamesrvAddr("192.168.64.141:9876:192.168.64.142:9876");
        //消费者注册
        c.setNamesrvAddr("192.168.64.141:9876");

        c.subscribe("Topic1", "TagA || TagB");
        //监听器，负责处理消息
        c.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext ctx) {
                for (MessageExt msg : list) {
                    System.out.println(
                            msg + "\n---------------- \n" +new String(msg.getBody()) );
                }
                //消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                //稍后再消费
//                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        //启动消费
        c.start();
        System.out.println("开始消费数据");
    }
}
