package cn.tedu.account.tcc;

import cn.tedu.account.entity.Account;
import cn.tedu.account.mapper.AccountMapper;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountTccActionImpl implements AccountTccAction{

    @Autowired
    private AccountMapper accountMapper;
    @Override
    public boolean perpareDecreaseAccount(
            BusinessActionContext context,
            Long userId,
            BigDecimal money) {
        //先查询账户是否存在和金额是否足够
        Account account = accountMapper.selectById(userId);
        //BigDecimal判断大小需要使用compareTo函数
        if (account==null||account.getResidue().compareTo(money)<0){
            return true;
        }
        //一阶段添加到冻结金额
        accountMapper.updateFrozen(
                userId,
                account.getResidue().subtract(money),
                account.getFrozen().add(money));
        //添加标记
        ResultHolder.setResult(
                AccountMapper.class,
                context.getXid(),
                "p");
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext context) {
        String p = ResultHolder.getResult(
                AccountMapper.class, context.getXid());
        if (p==null){return true;}
        //
        Long userId = Long.valueOf(
                context.getActionContext("userId").toString());
        BigDecimal money = new BigDecimal(
                context.getActionContext("money").toString());
        //提交
        accountMapper.updateFrozenToUsed(userId, money);
        //删除标记
        ResultHolder.removeResult(AccountMapper.class, context.getXid());
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext context) {
        String p = ResultHolder.getResult(
                AccountMapper.class, context.getXid());
        if (p==null){return true;}
        //
        Long userId = Long.valueOf(
                context.getActionContext("userId").toString());
        BigDecimal money = new BigDecimal(
                context.getActionContext("money").toString());
        //回滚
        accountMapper.updateFrozenToResidue(userId, money);
        //删除标记
        ResultHolder.removeResult(AccountMapper.class, context.getXid());
        return true;
    }
}
