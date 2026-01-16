package com.kersey.controller;


import com.kersey.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/util")
public class UtilController {

    Jedis jedis = new Jedis();

    @GetMapping()
    public Result getUser(){
        String username = jedis.get("username");
        String status = jedis.get("status");
        String id = jedis.get("id");
        String role=jedis.get("role");
        String password = jedis.get("password");
        String university = jedis.get("university");
        String credit = jedis.get("credit");
        String orderSum = jedis.get("orderSum");
        String stars = jedis.get("stars");
        User user = new User(Integer.parseInt(id),username,Integer.parseInt(status),Integer.parseInt(role),password,university,Integer.parseInt(credit),Integer.parseInt(orderSum),Float.parseFloat(stars));
        return new Result(Code.GET_OK,user);

    }

    @GetMapping("/{time}")
    public Result logOut(@PathVariable int time){
        String username = jedis.get("username");
        jedis.expire("username",time);
        jedis.expire("status",time);
        jedis.expire("id",time);
        return new Result(username!=null?Code.GET_OK:Code.GET_ERR,username);

    }

}
