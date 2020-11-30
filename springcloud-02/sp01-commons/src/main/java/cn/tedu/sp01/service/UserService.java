package cn.tedu.sp01.service;

import cn.tedu.sp01.pojo.User;

public interface UserService {
    //根据用户id获取用户
    User getUser(Integer userId);
    //根据用户id添加用户积分
    void addScore(Integer userId,Integer score);
}
