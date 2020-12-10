package cn.tedu.storage.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface StorageTccAction {
    @TwoPhaseBusinessAction(name = "StorageTccAction")
    boolean preparDecreaseStorage(
            BusinessActionContext context,
            @BusinessActionContextParameter(paramName = "productId") Long productId,
            @BusinessActionContextParameter(paramName = "count") Integer count
    );
    /*确认提交*/
    boolean commit(BusinessActionContext context);
    /*回滚*/
    boolean rollback(BusinessActionContext context);
}
