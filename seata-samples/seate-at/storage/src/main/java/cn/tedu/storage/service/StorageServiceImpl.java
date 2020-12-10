package cn.tedu.storage.service;

import cn.tedu.storage.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageServiceImpl implements StorageService{

    @Autowired
    private StorageMapper storageMapper;

    //商品id,数量
    @Transactional
    @Override
    public void decrease(Long productId, Integer count) throws Exception {
        storageMapper.decrease(productId,count);

        //模拟异常
        if(Math.random()>0.5){
            throw new RuntimeException("storage出现异常");
        }
    }
}
