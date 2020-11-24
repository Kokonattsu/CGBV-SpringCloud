package cn.tedu.sp06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Sp06RibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp06RibbonApplication.class, args);
    }

    @LoadBalanced //添加负载均衡功能
    @Bean
    public RestTemplate restTemplate(){
        SimpleClientHttpRequestFactory factory=new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(1000);    //建立连接等待时间
        factory.setReadTimeout(1000);       //连接建立发送请求后，等待接收响应的时间
        return new RestTemplate();
    }
}
