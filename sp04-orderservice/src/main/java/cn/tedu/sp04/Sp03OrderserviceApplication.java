package cn.tedu.sp04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient  //发现客户端
@EnableFeignClients     //feign客户端
@EnableCircuitBreaker   //断路器
@SpringBootApplication
public class Sp03OrderserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp03OrderserviceApplication.class, args);
    }

}
