package cn.tedu.account.controller;

import cn.tedu.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import java.math.BigDecimal;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    //http://localhost/decrease?userId=1&money=100
    @GetMapping("/decrease")
    public String decrease(Long userId, BigDecimal money){
        accountService.decrease(userId, money);
        return "账户"+userId+"金额扣减"+money+"元";
    }
}
