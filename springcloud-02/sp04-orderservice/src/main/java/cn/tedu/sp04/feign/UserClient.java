package cn.tedu.sp04.feign;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service",fallback = UserClientFB.class)
public interface UserClient {
    @GetMapping("/{userId}")
    JsonResult<User> getUser(@PathVariable("userId") Integer userId);

    @GetMapping("/{userId}/score")
    JsonResult addScore(
            @PathVariable("userId") Integer userId,
            @RequestParam("score") Integer score);

}
