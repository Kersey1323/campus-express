package com.kersey.controller;


import com.kersey.pojo.Order;
import com.kersey.pojo.PageBean;
import com.kersey.pojo.User;
import com.kersey.service.UserService;
import com.kersey.service.impl.UserServiceImpl;
import com.kersey.utils.CheckCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    HttpServletResponse response;

    @Autowired
    HttpSession session;
//根据用户名查询
    @GetMapping("/{username}")
    public Result selectByName(@PathVariable String username) {
        User user = userService.selectByName(username);
        return new Result(user!=null ? Code.GET_OK : Code.GET_ERR, user);

    }
//注册
    @PostMapping
    public Result insert(@RequestBody User user) {

        Jedis jedis = new Jedis();
        if (user.getCheckCode().equals(jedis.get("checkCode")))
        {
            boolean flag = userService.insert(user);
            return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag,"注册成功！");
        }
        else
            return new Result(Code.SAVE_ERR,false,"验证码错误！");
    }
//登录并缓存至redis
    @GetMapping("/{username}" + "/{password}")
    public Result login(@PathVariable String username, @PathVariable String password) {
        User user = new User(username,password);
        User flag = userService.login(user);
        if(flag!=null){

            Jedis jedis = new Jedis();
            jedis.set("username",flag.getUsername());
            jedis.set("status",String.valueOf(flag.getStatus()));
            jedis.set("id",String.valueOf(flag.getId()));
            jedis.set("role",String.valueOf(flag.getRole()));
            jedis.set("password",flag.getPassword());
            jedis.set("university",flag.getUniversity());
            jedis.set("credit",String.valueOf(flag.getCredit()));
            jedis.set("orderSum",String.valueOf(flag.getOrderSum()));
            jedis.set("stars",String.valueOf(flag.getStars()));
            jedis.expire("username",60*60*24*7);
            jedis.expire("status",60*60*24*7);
            jedis.expire("id",60*60*24*7);
            jedis.expire("role",60*60*24*7);
            jedis.expire("password",60*60*24*7);
            jedis.expire("university",60*60*24*7);
            jedis.expire("credit",60*60*24*7);
            jedis.expire("orderSum",60*60*24*7);
            jedis.expire("stars",60*60*24*7);

        }
        return new Result(flag!=null ? Code.GET_OK : Code.GET_ERR, flag);
    }


//    用户订单分页查询
    @GetMapping("/{currentPage}"+"/{size}"+"/{userId}")
    public Result selectOrderByUser(@PathVariable int currentPage,@PathVariable  int size,@PathVariable  int userId){
        List<Order> rows = userService.selectByUser(currentPage, size, userId);
        int totalCount = userService.orderCount(userId);
        PageBean<Order> orderPageBean = new PageBean<>(totalCount, rows);
        return new Result(rows!=null?Code.GET_OK:Code.GET_ERR,orderPageBean);

    }

//    用户分页查询
    @GetMapping("/admin/{currentPage}"+"/{size}")
    public Result selectUserByPage(@PathVariable int currentPage,@PathVariable  int size){
        List<User> rows = userService.selectUserByPage(currentPage, size);
        int totalCount = userService.userCount();
        PageBean<User> orderPageBean = new PageBean<>(totalCount, rows);
        return new Result(rows!=null?Code.GET_OK:Code.GET_ERR,orderPageBean);

    }

//    条件查询个数
    @GetMapping("/admin/{currentPage}"+"/{size}"+"/{condition}")
    public Result selectByConditions(@PathVariable int currentPage,@PathVariable  int size,@PathVariable  String condition){
        List<User> rows = userService.selectByConditions(currentPage, size,condition);
        System.out.println(rows);
        int totalCount = userService.conditionCount(condition);
        PageBean<User> orderPageBean = new PageBean<>(totalCount, rows);
        return new Result(rows!=null?Code.GET_OK:Code.GET_ERR,orderPageBean);

    }

//    获取验证码
    @GetMapping
    public void getCode() throws IOException {
        Jedis jedis = new Jedis();
        ServletOutputStream os = response.getOutputStream();
        String code = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);
        jedis.set("checkCode", code);

    }


//修改
    @PutMapping
    public Result update(@RequestBody User user){

    return new Result(userService.update(user)>0?Code.UPDATE_OK:Code.UPDATE_ERR,userService.update(user)>0);
    }

//注册配送员
    @PutMapping("/{id}")
    public Result beCurrier(@PathVariable int id){

        return new Result(userService.beCurrier(id)>0?Code.UPDATE_OK:Code.UPDATE_ERR,userService.beCurrier(id)>0);
    }

//批量积分增加
    @PutMapping("/{id}"+"/{length}")
    public Result creditAdds(@PathVariable int id,@PathVariable int length){

        return new Result(userService.creditAdds(id,length)>0?Code.UPDATE_OK:Code.UPDATE_ERR,userService.creditAdds(id,length)>0);
    }

//    积分增加
    @PutMapping("/pay/{id}")
    public Result creditAdd(@PathVariable int id){

        return new Result(userService.creditAdd(id)>0?Code.UPDATE_OK:Code.UPDATE_ERR,userService.creditAdd(id)>0);
    }
}
