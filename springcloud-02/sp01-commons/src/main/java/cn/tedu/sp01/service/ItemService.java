package cn.tedu.sp01.service;

import cn.tedu.sp01.pojo.Item;

import java.util.List;

public interface ItemService {
    //获取订单中的商品列表
    List<Item> getItem(String orderId);
    //减少商品库存
    void decreaseNumbers(List<Item> list);
}
