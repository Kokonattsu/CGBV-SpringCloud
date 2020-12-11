package cn.tedu.account.service.impl;

import cn.tedu.account.mapper.AccountMapper;
import cn.tedu.account.service.AccountService;
import cn.tedu.account.tcc.AccountTccAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountTccAction accountTccAction;

    //开启本地事务
    @Override
    public void decrease(Long userId, BigDecimal money) {
        accountTccAction.perpareDecreaseAccount(
                null,
                userId,
                money);
    }
}