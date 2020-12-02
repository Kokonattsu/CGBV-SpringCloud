package cn.tedu.sp08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication
public class Sp08HystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp08HystrixDashboardApplication.class, args);
    }

}