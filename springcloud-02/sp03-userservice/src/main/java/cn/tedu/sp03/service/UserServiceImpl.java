package cn.tedu.sp03.service;

import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.UserService;
import cn.tedu.web.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Value("${sp.user-service.users}")
    private String userJson;
    //查询用户
    @Override
    public User getUser(Integer userId) {

        List<User> list = JsonUtil.from(
                userJson, new TypeReference<List<User>>() {});



        for (User user:list){
            boolean equals = user.getId().equals(userId);
            if (equals){
                return user;
            }
        }
        return null;

    }
    //添加积分
    @Override
    public void addScore(Integer userId, Integer score) {
    // 这里增加积分
        log.info("user "+userId+" - 增加积分 "+score);

    }
}
