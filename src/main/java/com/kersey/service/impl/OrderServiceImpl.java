package com.kersey.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.kersey.mapper.OrderMapper;
import com.kersey.pojo.Order;
import com.kersey.pojo.PageBean;
import com.kersey.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public boolean add(Order order) {

        int flag = orderMapper.add(order);
        return flag>0;
    }

    @Override
    public Order selectById(String orderId) {

        return orderMapper.selectById(orderId);
    }

    @Override
    public List<Order> selectByPage(int begin, int size) {
        int currentPage=(begin-1)* size;
       return orderMapper.selectByPage(currentPage,size);
    }

    @Override
    public int selectCount() {
        return orderMapper.selectCount();
    }


    @Override
    public boolean deleteById(String orderId) {
        return orderMapper.deleteById(orderId)>0;
    }

    @Override
    public boolean update(Order order) {
        return orderMapper.update(order)>0;
    }

    @Override
    public List<Order> selectByConditions(int begin, int size, String condition) {
        int currentPage=(begin-1)*size;
        return orderMapper.selectByConditions(currentPage, size, condition);
    }

    @Override
    public int selectCountOfCondition(String condition) {
        return orderMapper.selectCountOfCondition(condition);
    }

    @Override
    public int deleteByIds(List<String> ids) {
        return orderMapper.deleteByIds(ids);
    }

    @Override
    public int addUserAndOrder(String orderId, int userId) {
        return orderMapper.addUserAndOrder(orderId,userId);
    }

    @Override
    public int deleteUserAndOrder(String orderId) {
        return orderMapper.deleteUserAndOrder(orderId);
    }

    @Override
    public int deleteUserAndOrderS(List<String> orderIds) {
        return orderMapper.deleteUserAndOrderS(orderIds);
    }

    @Override
    public int pay(String orderId) {
        return orderMapper.pay(orderId);
    }

    @Override
    public int payByIds(List<String> ids) {
        return orderMapper.payByIds(ids);
    }

    @Override
    public int orderDelivery(String orderId) {
        return orderMapper.orderDelivery(orderId);
    }

    @Override
    public int orderDeny(String orderId) {
        return orderMapper.orderDeny(orderId);
    }

    @Override
    public int orderCountAdd(int month) {
        return orderMapper.orderCountAdd(month);
    }
}


