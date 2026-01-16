package com.kersey.controller;


import com.kersey.pojo.DelOrder;
import com.kersey.pojo.Order;
import com.kersey.pojo.PageBean;
import com.kersey.service.DelOrderService;
import com.kersey.service.impl.DelOrderServiceImpl;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delOrders")
public class DelOrderController {
    @Autowired
    DelOrderService delOrderService;

//    分页查询待代取订单
    @GetMapping("/{currentPage}"+"/{pageSize}")
    public Result selectByPage(@PathVariable int currentPage,@PathVariable int pageSize){

        List<Order> rows = delOrderService.selectByPage(currentPage, pageSize);
        int totalCount = delOrderService.selectCount();
        PageBean<Order> pageBean = new PageBean<>(totalCount, rows);
        return new Result(rows!=null?Code.GET_OK:Code.GET_ERR,pageBean);
    }

//    接单
    @PutMapping("/{orderId}")
    public Result orderReceive(@PathVariable String orderId){

        return new Result(delOrderService.orderReceive(orderId)>0?Code.UPDATE_OK:Code.UPDATE_ERR,delOrderService.orderReceive(orderId)>0);
    }

//    订单完成
    @PutMapping("/finish/{orderId}")
    public Result orderFinish(@PathVariable String orderId){
        return new Result(delOrderService.orderFinish(orderId)>0?Code.UPDATE_OK:Code.UPDATE_ERR,delOrderService.orderFinish(orderId)>0);
    }

//    关联表添加
    @PostMapping("/receive/{currierId}")
    public Result orderAndCurrier(@RequestBody DelOrder delOrder,@PathVariable int currierId){

        return new Result(delOrderService.orderAndCurrier(delOrder,currierId)>0?Code.SAVE_OK:Code.SAVE_ERR,delOrderService.orderAndCurrier(delOrder,currierId)>0);
    }

    @PutMapping("/{orderId}/"+"{value}")
    public Result orderRemark(@PathVariable String orderId,@PathVariable int value ){

        return new Result(delOrderService.orderRemark(orderId,value)>0?Code.UPDATE_OK:Code.UPDATE_ERR,delOrderService.orderRemark(orderId,value));
    }
    //评价完成
    @PutMapping("/remark/{orderId}")
    public Result isRemark(@PathVariable String orderId){
        return new Result(delOrderService.isRemark(orderId)>0?Code.UPDATE_OK:Code.UPDATE_ERR,delOrderService.isRemark(orderId)>0);
    }

//    反馈
    @PostMapping("/{orderId}")
    public Result feedBack(@PathVariable String orderId,@RequestBody String feedBack){

        return new Result(delOrderService.feedBack(orderId,feedBack)>0?Code.SAVE_OK:Code.SAVE_ERR,delOrderService.feedBack(orderId,feedBack)>0);
    }

//    查询配送员订单

    @GetMapping("/{currierId}"+"/{currentPage}"+"/{pageSize}")
    public Result myOrder(@PathVariable int currierId ,@PathVariable int currentPage,@PathVariable int pageSize){

        List<Order> rows = delOrderService.myOrder(currierId,currentPage, pageSize);
        int totalCount = delOrderService.myOrderCount(currierId);
        PageBean<Order> pageBean = new PageBean<>(totalCount, rows);
        return new Result(rows!=null?Code.GET_OK:Code.GET_ERR,pageBean);
    }

    @PutMapping("/static/{value}")
    public Result staticAdd(@PathVariable int value){
        return new Result(delOrderService.staticAdd(value)>0?Code.UPDATE_OK: Code.UPDATE_ERR,delOrderService.staticAdd(value)>0);
    }

    @PutMapping("/static")
    public Result sumAdd(){
        return new Result(delOrderService.sumAdd()>0?Code.UPDATE_OK: Code.UPDATE_ERR,delOrderService.sumAdd()>0);
    }

    @GetMapping("/underReceive/{condition}")
    public Result searchAllOfNotReceive(@PathVariable String condition){
        List<Order> rows = delOrderService.searchAllNotReceive(condition);
        int totalCount = delOrderService.searchAllNotReceiveOfCount(condition);
        PageBean<Order> pageBean = new PageBean<>(totalCount, rows);
        return new Result(rows!=null?Code.GET_OK:Code.GET_ERR,pageBean);

    }

    @GetMapping("/receive/{currierId}/{condition}")
    public Result searchAllReceive(@PathVariable int currierId,@PathVariable String condition){
        List<Order> rows = delOrderService.searchAllReceive(currierId,condition);
        int totalCount = delOrderService.searchAllReceiveOfCount(currierId,condition);
        PageBean<Order> pageBean = new PageBean<>(totalCount, rows);
        return new Result(rows!=null?Code.GET_OK:Code.GET_ERR,pageBean);

    }

}
