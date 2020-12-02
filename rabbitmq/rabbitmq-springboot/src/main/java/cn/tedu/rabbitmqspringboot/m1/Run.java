package cn.tedu.rabbitmqspringboot.m1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Run {

    @Autowired
    private Producer producer;

    public static void main(String[] args) {
        SpringApplication.run(Run.class,args);
    }

    @Bean
    public Queue rabbitQueue(){
        return new Queue("rabbit",false);
    }

    //springboot完成初始化后执行
    @PostConstruct
    public void test(){
        producer.send();
    }

}
