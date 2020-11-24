package cn.tedu.sp06.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 使用RestTemplate远程调用item-service
     * url：http://localhost:8001/{orderId}
     */
    @GetMapping("/getItem/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId){
        return restTemplate.getForObject("http://localhost:8001/{1}", JsonResult.class,orderId);

    }

    //http://localhost:8001/deleteItem/
    //访问http://localhost:3001/deleteItem/
    @PostMapping("/deleteItem")
    public JsonResult doDecreaseNumbers(@RequestBody List<Item> itemList){
        return restTemplate.postForObject(
                "http://localhost:8001/deleteItem",
                itemList,
                JsonResult.class );
    }

}
