package com.kersey.service.impl;

import com.kersey.mapper.DelOrderMapper;
import com.kersey.pojo.DelOrder;
import com.kersey.pojo.Order;
import com.kersey.service.DelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelOrderServiceImpl implements DelOrderService {

    @Autowired
    DelOrderMapper delOrderMapper;


    @Override
    public List<Order> selectByPage(int begin, int size) {

        int currentPage=(begin-1)*size;
        return delOrderMapper.selectByPage(currentPage,size);
    }

    @Override
    public int selectCount() {
        return delOrderMapper.selectCount();
    }

    @Override
    public int orderAndCurrier(DelOrder delOrder,int currierId) {

        return delOrderMapper.orderAndCurrier(delOrder,currierId);
    }

    @Override
    public int orderReceive(String orderId) {
        return delOrderMapper.orderReceive(orderId);
    }

    @Override
    public int orderFinish(String orderId) {
        return delOrderMapper.orderFinish(orderId);
    }

    @Override
    public int orderRemark(String orderId, int value) {
        return delOrderMapper.orderRemark(orderId,value);
    }

    @Override
    public int isRemark(String orderId) {
        return delOrderMapper.isRemark(orderId);
    }

    @Override
    public int feedBack(String orderId, String feedBack) {
        return delOrderMapper.feedBack(orderId,feedBack);
    }

    @Override
    public List<Order> myOrder(int currierId, int begin, int size) {
        int currentPage=(begin-1)*size;
        return delOrderMapper.myOrder(currierId,currentPage,size);
    }

    @Override
    public int myOrderCount(int currierId) {
        return delOrderMapper.myOrderCount(currierId);
    }

    @Override
    public int staticAdd(int value) {
        return delOrderMapper.staticAdd(value);
    }

    @Override
    public int sumAdd() {
        return delOrderMapper.sumAdd();
    }

    @Override
    public List<Order> searchAllNotReceive(String condition) {
        return delOrderMapper.searchAllNotReceived(condition);
    }

    @Override
    public int searchAllNotReceiveOfCount(String condition) {
        return delOrderMapper.searchAllNotReceivedOfCount(condition);
    }

    @Override
    public List<Order> searchAllReceive(int currierId,String condition) {
        return delOrderMapper.searchAllReceived(currierId,condition);
    }

    @Override
    public int searchAllReceiveOfCount(int currierId,String condition) {
        return delOrderMapper.searchAllReceivedOfCount(currierId,condition);
    }
}
