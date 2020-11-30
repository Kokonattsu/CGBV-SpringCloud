package cn.tedu.sp02.item.service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class itemServiceImpl implements ItemService {
    private static List<Item> itemList=new ArrayList<>();
    static {
        itemList.add(new Item(1,"商品A",99));
        itemList.add(new Item(2,"商品B",99));
        itemList.add(new Item(3,"商品C",99));
        itemList.add(new Item(4,"商品D",99));
        itemList.add(new Item(5,"商品E",99));
    }
    //根据订单id获取商品
    @Override
    public List<Item> getItem(String orderId) {


        return itemList;
    }

    //减少商品
    @Override
    public void decreaseNumbers(List<Item> list) {

        for (Item item:list){

            System.out.println("减少商品："+item);
        }
    }
}
