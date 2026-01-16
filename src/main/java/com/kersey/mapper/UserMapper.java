package com.kersey.mapper;

import com.kersey.pojo.Order;
import com.kersey.pojo.PageBean;
import com.kersey.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {

    public int insert(User user);

    public User selectByName(String username);

    public User login(User user);

    public List<Order> selectOrderByUser(int begin,int size,int userId);

    public int orderCount(int userId);

    public int update(User user);

    public int beCurrier(int id);

    public int creditAdds(int id,int length);

    public int creditAdd(int id);

    public List<User> selectUserByPage(int begin,int size);

    @Select("select count(*) from tb_user")
    public int userCount();

    public List<User> selectByConditions(int begin,int size,String condition);

    public int conditionCount(String condition);
}
