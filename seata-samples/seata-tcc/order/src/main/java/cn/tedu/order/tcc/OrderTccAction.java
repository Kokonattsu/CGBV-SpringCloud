package cn.tedu.order.tcc;

import cn.tedu.order.entity.Order;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

@LocalTCC
public interface OrderTccAction {
    /*try 预留资源*/
    @TwoPhaseBusinessAction(
            name = "OrderTccAction",//
            commitMethod = "commit",
            rollbackMethod = "rollback"
    )
    public boolean preparCreateOrder(
            //上下文对象
            BusinessActionContext context,
            //将属性保存到上下文对象中
            @BusinessActionContextParameter(paramName = "orderId") Long orderId,
            @BusinessActionContextParameter(paramName = "userId") Long userId,
            @BusinessActionContextParameter(paramName = "productId") Long productId,
            @BusinessActionContextParameter(paramName = "count") Integer count,
            @BusinessActionContextParameter(paramName = "money") BigDecimal money
    );
    /*确认提交*/
    public boolean commit(BusinessActionContext context);
    /*回滚*/
    public boolean rollback(BusinessActionContext context);
}
