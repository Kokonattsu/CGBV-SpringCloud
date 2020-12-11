package cn.tedu.account.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

@LocalTCC
public interface AccountTccAction {
    @TwoPhaseBusinessAction(name = "AccountTccAction")
    boolean perpareDecreaseAccount(
            BusinessActionContext context,
            @BusinessActionContextParameter(paramName = "userId") Long userId,
            @BusinessActionContextParameter(paramName = "money")BigDecimal money
    );

    boolean commit(BusinessActionContext context);

    boolean rollback(BusinessActionContext context);
}
