package cn.tedu.account.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public interface AccountService {


    void decrease(Long userId, BigDecimal money);
}
