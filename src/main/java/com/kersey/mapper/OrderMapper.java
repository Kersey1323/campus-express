package com.kersey.mapper;

import com.kersey.pojo.Order;
import com.kersey.pojo.PageBean;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface OrderMapper {

    //    添加
    public int add(Order order);

    //    根据id查询
    public Order selectById(String orderId);

    //    分页查询
    public List<Order> selectByPage(int begin, int size);

    //    查询订单个数
    @Select("select count(*) from order_info")
    public int selectCount();

    //    根据id删除订单
    @Delete("delete from order_info where id=#{orderId} ")
    public int deleteById(String orderId);

    //    更改
    public int update(Order order);

    //    条件查询
    public List<Order> selectByConditions(int begin, int size, String condition);

    //    条件查询个数
    public int selectCountOfCondition(String condition);

    //    批量删除订单表
    public int deleteByIds(@Param("ids") List<String> ids);

    //    添加用户订单表
    public int addUserAndOrder(String orderId, int userId);

    //    删除订单用户表
    public int deleteUserAndOrder(String orderId);

    //    批量删除订单用户表
    public int deleteUserAndOrderS(@Param("ids") List<String> orderIds);

//    订单支付
    public int pay(String orderId);

//    批量订单支付
    public int payByIds(@Param("ids") List<String> ids);

//    代取下单
    public int orderDelivery(String orderId);

//    取消代取下单
    public int orderDeny(String orderId);

    @Update("update chart_order set count=count+1 where id=#{month}")
    public int orderCountAdd(int month);
}
