package com.kersey.controller;


import com.kersey.pojo.Order;
import com.kersey.pojo.PageBean;
import com.kersey.service.OrderService;
import com.kersey.utils.OrderCodeUtil;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@RestController
@RestControllerAdvice
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

//    添加订单
    @PostMapping
    public Result add(@RequestBody Order order){
        boolean flag = orderService.add(order);
        int f = orderService.addUserAndOrder(order.getOrderId(), order.getUserId());

        return new Result(flag&&f>0?Code.SAVE_OK:Code.SAVE_ERR,flag&&f>0);

    }

//    根据订单号查询
    @GetMapping("/{orderId}")
    public Result selectById(@PathVariable String orderId){
        Order order = orderService.selectById(orderId);
        return new Result(order!=null?Code.GET_OK:Code.GET_ERR,order);
    }

//    生成订单号,存入redis缓存中
    @GetMapping
    public String getOrderId(){
        String code = OrderCodeUtil.getCode("code");
        Jedis jedis=new Jedis();
        return jedis.lindex("code", jedis.llen("code") - 1);

    }


//    分页查询
    @GetMapping("/{currentPage}"+"/{pageSize}")
    public Result selectByPage(@PathVariable int currentPage,@PathVariable int pageSize){

        List<Order> rows = orderService.selectByPage(currentPage, pageSize);
        int selectCount = orderService.selectCount();
        PageBean<Order> pageBean = new PageBean<>(selectCount, rows);

        return new Result(rows!=null?Code.GET_OK:Code.GET_ERR,pageBean);



    }

//    删除
    @DeleteMapping("/{orderId}")
    public Result deleteById(@PathVariable String orderId){
        boolean f = orderService.deleteById(orderId);
        int flag = orderService.deleteUserAndOrder(orderId);
        return new Result(f&&flag>0?Code.DELETE_OK:Code.DELETE_ERR,f&&flag>0);

    }

//    修改
    @PutMapping
    public Result update(@RequestBody Order order){
        boolean flag = orderService.update(order);
        return new Result(flag?Code.UPDATE_OK:Code.DELETE_ERR,flag);

    }

//    条件查询
    @GetMapping("/{currentPage}"+"/{size}"+"/{condition}")
    public Result selectByConditions(@PathVariable int currentPage,@PathVariable int size,@PathVariable String condition){
        List<Order> rows = orderService.selectByConditions(currentPage,size,condition);
        int count = orderService.selectCountOfCondition(condition);
        PageBean<Order> orderPageBean = new PageBean<>(count, rows);
        return new Result(rows!=null?Code.GET_OK:Code.GET_ERR,orderPageBean);

    }

//    批量删除
    @DeleteMapping()
    public Result deleteByIds(@RequestBody List<String> ids){
        int flag = orderService.deleteByIds(ids);
        int f = orderService.deleteUserAndOrderS(ids);
        System.out.println(ids);
        return new Result(flag>0&&f>0?Code.DELETE_OK:Code.DELETE_ERR,flag>0&&f>0);

    }

//    支付
    @PutMapping("/pay/{orderId}")
    public Result pay(@PathVariable String orderId){

        return new Result(orderService.pay(orderId)>0?Code.UPDATE_OK:Code.UPDATE_ERR,orderService.pay(orderId)>0);
    }

//    批量支付

    @PutMapping("/pay")
    public Result payByIds(@RequestBody List<String> ids){

        return new Result(orderService.payByIds(ids)>0?Code.UPDATE_OK:Code.UPDATE_ERR,orderService.payByIds(ids)>0);
    }

    @PutMapping("/{orderId}")
    public Result orderDelivery(@PathVariable String orderId){

        return new Result(orderService.orderDelivery(orderId)>0?Code.UPDATE_OK:Code.UPDATE_ERR,orderService.orderDelivery(orderId)>0);
    }

    @PutMapping("/deny/{orderId}")
    public Result orderDeny(@PathVariable String orderId){

        return new Result(orderService.orderDeny(orderId)>0?Code.UPDATE_OK:Code.UPDATE_ERR,orderService.orderDeny(orderId)>0);
    }

    @PutMapping("/static/{month}")
    public Result orderCountAdd(@PathVariable int month){
        return new Result(orderService.orderCountAdd(month)>0?Code.UPDATE_OK:Code.UPDATE_ERR,orderService.orderCountAdd(month)>0);
    }
}
