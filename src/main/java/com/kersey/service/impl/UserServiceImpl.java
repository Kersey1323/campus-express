package com.kersey.service.impl;

import com.kersey.mapper.UserMapper;
import com.kersey.pojo.Order;
import com.kersey.pojo.User;
import com.kersey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByName(String username) {

        return userMapper.selectByName(username);

    }

    @Override
    public boolean insert(User user) {
        int flag = userMapper.insert(user);
        return flag > 0;
    }

    @Override
    public User login(User user) {
        User flag = userMapper.login(user);
        return flag;
    }

    @Override
    public List<Order> selectByUser(int begin, int size, int userId) {
        int currentPage=(begin-1)*size;
        return userMapper.selectOrderByUser(currentPage,size,userId);
    }

    @Override
    public int orderCount(int userId) {
        return userMapper.orderCount(userId);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int creditAdds(int id, int length) {
        return userMapper.creditAdds(id,length);
    }

    @Override
    public int beCurrier(int id) {
        return userMapper.beCurrier(id);

    }

    @Override
    public int creditAdd(int id) {
        return userMapper.creditAdd(id);
    }

    @Override
    public List<User> selectUserByPage(int begin, int size) {
        int currentPage=(begin-1)*size;
        return userMapper.selectUserByPage(currentPage,size);
    }

    @Override
    public int userCount() {
        return userMapper.userCount();
    }

    @Override
    public List<User> selectByConditions( int begin, int size,String condition) {
        int currentPage=(begin-1)*size;
        return userMapper.selectByConditions(currentPage,size,condition);
    }

    @Override
    public int conditionCount(String condition) {
        return userMapper.conditionCount(condition);
    }
}
