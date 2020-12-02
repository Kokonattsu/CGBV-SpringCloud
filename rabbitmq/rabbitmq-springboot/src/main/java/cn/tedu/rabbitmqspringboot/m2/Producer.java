package cn.tedu.rabbitmqspringboot.m2;

import javafx.beans.binding.When;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    private AmqpTemplate amqpTemplate;



    public void send(){
//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//
//            }
//        }).start();
        for (int i=1;i>0;i++){
            amqpTemplate.convertAndSend("task-rabbit",String.valueOf(i));

        }
//        new Thread(()->{
//
//        });

    }
}
