package cn.tedu.rabbitmqspringboot.m3;

import cn.tedu.rabbitmqspringboot.m1.Run;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Run3 {

    @Autowired
    private Producer producer;

    public static void main(String[] args) {
        SpringApplication.run(Run3.class,args);
    }

    @Bean
    public FanoutExchange logs(){
        return new FanoutExchange("logs",false,false);
    }
    //springboot完成初始化后执行
    @PostConstruct
    public void test(){
        producer.send();
    }

}
