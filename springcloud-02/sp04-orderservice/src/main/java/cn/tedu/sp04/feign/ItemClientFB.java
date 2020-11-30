package cn.tedu.sp04.feign;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ItemClientFB implements ItemClient{

    private static List<Item> itemList=new ArrayList<>();

    static {
        itemList.add(new Item(1,"商品A",99));
        itemList.add(new Item(2,"商品B",99));
        itemList.add(new Item(3,"商品C",99));
        itemList.add(new Item(4,"商品D",99));
    }

    @Override
    public JsonResult<List<Item>> doGetItem(String orderId) {
        if (Math.random()<0.5){
            return JsonResult.ok().data(itemList);
        }
        return JsonResult.err().msg("获取订单商品列表失败.order-service");
    }

    @Override
    public JsonResult doDecreaseNumbers(List<Item> itemList) {
        return JsonResult.err().msg("减少商品库存失败.order-service");
    }
}
