package cn.tedu.sp03.controller;

import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.UserService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Value("${server.port}")
    private String port;

    //http://localhost:8101/7
    @GetMapping("/{userId}")
    public JsonResult<User> getUser(@PathVariable Integer userId) throws InterruptedException {
        ///--设置随机延迟
//        if(Math.random()<0.6) {
//            long t = new Random().nextInt(5000);
//            log.info("item-service-"+port+" doGetItem- 暂停 "+t);
//            Thread.sleep(t);
//        }

        log.info("get user, userId="+userId);
        User u = userService.getUser(userId);
        return JsonResult.ok(u);
    }
    //http://localhost:8101/7/score?score=123
    @GetMapping("/{userId}/score")
    public JsonResult addScore(
            @PathVariable Integer userId, Integer score) throws InterruptedException {
        ///--设置随机延迟
//        if(Math.random()<0.6) {
//            long t = new Random().nextInt(5000);
//            log.info("item-service-"+port+" doGetItem- 暂停 "+t);
//            Thread.sleep(t);
//        }
        userService.addScore(userId, score);
        return JsonResult.ok().data(userId+"增加积分"+score);
    }
}
