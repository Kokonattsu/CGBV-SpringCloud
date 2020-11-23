package cn.tedu.sp02.item.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import cn.tedu.web.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class itemController {
    @Autowired
    private ItemService itemService;
    @Value("${server.port}")
    private String port;

    @GetMapping("/{orderId}")
    public JsonResult doGetItem(@PathVariable String orderId){
        List<Item> itemList = itemService.getItem("");
        return JsonResult.ok().data(itemList).msg(port);
    }

    @PostMapping("/deleteItem")
    public JsonResult doDecreaseNumbers(
            @RequestBody List<Item> itemList){
        itemService.decreaseNumbers(itemList);
        return JsonResult.ok().msg(port);
    }
}
