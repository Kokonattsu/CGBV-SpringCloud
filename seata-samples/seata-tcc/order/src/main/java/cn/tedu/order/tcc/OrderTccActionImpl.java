package cn.tedu.order.tcc;

import cn.tedu.order.entity.Order;
import cn.tedu.order.mapper.OrderMapper;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderTccActionImpl implements OrderTccAction{

    @Autowired
    private OrderMapper orderMapper;
    /**/
    @Override
    public boolean preparCreateOrder(
            BusinessActionContext context, Long orderId,
            Long userId, Long productId, Integer count, BigDecimal money) {
        orderMapper.create(new Order(orderId,userId,productId,count,money,0));
        //getXid事务ID，"p"任意的标记
        ResultHolder.setResult(OrderTccAction.class,context.getXid(),"p");
        return true;
    }
    /*提交事务，把订单状态变成正常的1状态*/
    @Override
    public boolean commit(BusinessActionContext context) {
        //如果标记不为空则执行提交操作
        String p = ResultHolder.getResult(OrderTccAction.class, context.getXid());
        if(p==null){
            return true;
        }

        Long orderId = Long.valueOf(
                context.getActionContext("orderId").toString());
        orderMapper.updateStatus(orderId,1);
        //提交后删除标记
        ResultHolder.removeResult(OrderTccAction.class,context.getXid());
        return true;
    }
    /*事务失败时删除未提交的订单（或将订单变成已删除的-1状态）*/
    @Override
    public boolean rollback(BusinessActionContext context) {
        Long orderId = Long.valueOf(
                context.getActionContext("orderId").toString());
        orderMapper.deleteById(orderId);


        return true;
    }
}
