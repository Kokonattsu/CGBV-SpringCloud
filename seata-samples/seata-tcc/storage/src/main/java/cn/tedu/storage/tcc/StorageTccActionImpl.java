package cn.tedu.storage.tcc;

import cn.tedu.storage.entity.Storage;
import cn.tedu.storage.mapper.StorageMapper;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StorageTccActionImpl implements StorageTccAction{

    @Autowired
    private StorageMapper storageMapper;

    @Override
    public boolean preparDecreaseStorage(
            BusinessActionContext context,
            Long productId, Integer count) {
        //判断库存是否足够
        Storage storage = storageMapper.selectById(productId);
        if (storage==null||storage.getResidue()<count){
            throw  new RuntimeException("库存不足");
        }
        //减少剩余库存，增加冻结库存
        storageMapper.updateFrozen(
                productId,
                storage.getResidue()-count,
                count);
        //添加标记
        ResultHolder.setResult(
                getClass(), context.getXid(), "p");
        log.info("添加了事务->{ }"+context.getXid());
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext context) {
        //如果已经提交过了就直接返回
        String p = ResultHolder.getResult(getClass(), context.getXid());
        if (p==null){return true;}
        Long productId = Long.valueOf(
                context.getActionContext("productId").toString());
        Integer count = Integer.valueOf(
                context.getActionContext("count").toString());
        //提交事务
        storageMapper.updateFrozenToUsed(productId,count);
        //删除标记
        ResultHolder.removeResult(getClass(), context.getXid());
        log.info("提交了事务{ }"+context.getXid());
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext context) {
        //如果已经回滚过了就直接返回
        String p = ResultHolder.getResult(getClass(), context.getXid());
        Long productId = Long.valueOf(
                context.getActionContext("productId").toString());
        Integer count = Integer.valueOf(
                context.getActionContext("count").toString());
        //回滚事务
        storageMapper.updateFrozenToResidue(productId,count);
        //删除标记
        ResultHolder.removeResult(getClass(), context.getXid());
        log.info("回滚了事务->{ }"+context.getXid());
        return true;
    }
}
