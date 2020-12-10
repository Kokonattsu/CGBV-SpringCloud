package cn.tedu.storage.controller;

import cn.tedu.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    //http://localhost:8082/decrease?productId=1&count=10
    @GetMapping("/decrease")
    public String decrease(Long productId, Integer count) throws Exception {
        storageService.decrease(productId, count);
        return "商品"+productId+"库存减少"+count+"件";
    }
}
