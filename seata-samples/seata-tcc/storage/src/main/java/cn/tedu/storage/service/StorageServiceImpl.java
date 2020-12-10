package cn.tedu.storage.service;

import cn.tedu.storage.mapper.StorageMapper;
import cn.tedu.storage.tcc.StorageTccAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageServiceImpl implements StorageService{

    @Autowired
    private StorageMapper storageMapper;
    @Autowired
    private StorageTccAction storageTccAction;

    //商品id,数量

    @Override
    public void decrease(Long productId, Integer count) throws Exception {
        //创建事务
        storageTccAction.preparDecreaseStorage(
                null,
                productId,
                count);

        //模拟异常
//        if(Math.random()>0.5){
//            throw new RuntimeException("storage出现异常");
//        }
    }
}
