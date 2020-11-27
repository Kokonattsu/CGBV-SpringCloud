package cn.tedu.sp02.item.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@Slf4j
public class itemController {
    @Autowired
    private ItemService itemService;
    @Value("${server.port}")
    private String port;

    @GetMapping("/{orderId}")
    public JsonResult<List<Item>> doGetItem(@PathVariable String orderId) throws InterruptedException {

        ///--设置随机延迟
        if(Math.random()<0.9) {
            long t = new Random().nextInt(5000);
            log.info("item-service-"+port+" doGetItem- 暂停 "+t);
            Thread.sleep(t);
        }

        List<Item> itemList = itemService.getItem("");
        return JsonResult.ok().data(itemList).msg(port);
    }

    @PostMapping("/deleteItem")
    public JsonResult doDecreaseNumbers(
            @RequestBody List<Item> itemList) throws InterruptedException {
        ///--设置随机延迟
        if(Math.random()<0.6) {
            long t = new Random().nextInt(5000);
            log.info("item-service-"+port+" doGetItem- 暂停 "+t);
            Thread.sleep(t);
        }
        itemService.decreaseNumbers(itemList);
        return JsonResult.ok().msg(port);
    }
}
