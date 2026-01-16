package com.kersey.mapper;

import com.kersey.pojo.DelOrder;
import com.kersey.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DelOrderMapper {

    public List<Order> selectByPage(int begin, int size);

    @Select("select count(*) from order_info where isPay=1 and status=1 and isReceived=0")
    public int selectCount();

    public int orderReceive(String orderId);

    public List<Order> searchAllNotReceived(String condition);

    public int searchAllNotReceivedOfCount(String condition);

    public List<Order>  searchAllReceived(int currierId,String condition);

    public int searchAllReceivedOfCount(int currierId,String condition);

    public int orderFinish(String orderId);

    public int orderAndCurrier(DelOrder delOrder,int currierId);

    public int orderRemark(String orderId,int value);

    public int staticAdd(int value);

    @Update("update star_static_1 set count=count+1 where id=6")
    public int sumAdd();

    @Update("update order_info set isRemark=1 where id=#{orderId}")
    public int isRemark(String orderId);

    @Insert("update order_info set feedBack=#{feedBack} where id=#{orderId}")
    public int feedBack(String orderId,String feedBack);

    public List<Order> myOrder(int currierId,int begin, int size);

    @Select("select count(*)/2 from currier_order where currierId=#{currierId}")
    public int myOrderCount(int currierId);
}
