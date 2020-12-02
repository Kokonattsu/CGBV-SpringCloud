package cn.tedu.rabbitmqspringboot.m4;

import cn.tedu.rabbitmqspringboot.m3.Run3;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Run4 {

    @Autowired
    private Producer4 producer;

    public static void main(String[] args) {
        SpringApplication.run(Run4.class,args);
    }

    @Bean
    public DirectExchange fanoutExchange() {
        return new DirectExchange("direct_logs");
    }

    //springboot完成初始化后执行
    @PostConstruct
    public void test(){
        producer.send();
    }

}
