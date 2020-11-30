package cn.tedu.sp09.feign;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemClientFB implements ItemClient{

    @Override
    public JsonResult doGetItem(String orderId) {
        return JsonResult.err().msg("feign远程获取订单商品列表失败");
    }

    @Override
    public JsonResult doDecreaseNumbers(List<Item> itemList) {
        return JsonResult.err().msg("feign远程减少商品库存失败");
    }
}
