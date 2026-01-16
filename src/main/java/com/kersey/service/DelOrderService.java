package com.kersey.service;


import com.kersey.pojo.DelOrder;
import com.kersey.pojo.Order;

import java.util.List;

public interface DelOrderService {

    public List<Order> selectByPage(int begin,int size);

    public int selectCount();

    public int orderReceive(String orderId);

    public int orderFinish(String orderId);

    public int orderAndCurrier(DelOrder delOrder,int currierId);

    public int orderRemark(String orderId,int value);

    public int isRemark(String orderId);

    public int feedBack(String orderId,String feedBack);

    public List<Order> myOrder(int currierId,int begin,int size);

    public int myOrderCount(int currierId);

    public int staticAdd(int value);

    public int sumAdd();

    public List<Order> searchAllNotReceive(String condition);

    public int searchAllNotReceiveOfCount(String condition);

    public List<Order> searchAllReceive(int currierId,String condition);

    public int searchAllReceiveOfCount(int currierId, String condition);
}
