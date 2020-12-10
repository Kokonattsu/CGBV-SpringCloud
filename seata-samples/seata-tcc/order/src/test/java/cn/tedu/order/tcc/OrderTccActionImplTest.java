package cn.tedu.order.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderTccActionImplTest implements OrderTccAction{

    @Test
    void preparCreateOrder() {

        System.out.println(OrderTccAction.class);
    }

    @Override
    public boolean preparCreateOrder(BusinessActionContext context, Long orderId, Long userId, Long productId, Integer count, BigDecimal money) {
        return false;
    }

    @Override
    public boolean commit(BusinessActionContext context) {
        return false;
    }

    @Override
    public boolean rollback(BusinessActionContext context) {
        return false;
    }
}