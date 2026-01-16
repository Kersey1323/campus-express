package com.kersey.service;


import com.kersey.pojo.Order;
import com.kersey.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    public User selectByName(String username);

    public boolean insert(User user);

    public User login(User user);

    public List<Order> selectByUser(int begin,int size,int userId);

    public int orderCount(int userId);

    public int update(User user);

    public int beCurrier(int id);

    public int creditAdds(int id,int length);

    public int creditAdd(int id);

    public List<User> selectUserByPage(int begin,int size);

    public int userCount();

    public List<User> selectByConditions(int begin,int size,String condition);

    public int conditionCount(String condition);
}
