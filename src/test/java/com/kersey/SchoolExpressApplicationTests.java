package com.kersey;

import com.kersey.mapper.ChartMapper;
import com.kersey.mapper.UserMapper;
import com.kersey.pojo.Order;
import com.kersey.pojo.User;
import com.kersey.service.ChartService;
import com.kersey.service.OrderService;
import com.kersey.service.UserService;
import com.kersey.utils.OrderCodeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SchoolExpressApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ChartService chartService;

    @Autowired
    private ChartMapper chartMapper;
    @Test
    void test1() {
    chartMapper.getChartRole();

    }

    @Test
    void insertTest(){

    }
    @Test
    void selectTest(){
        User user = userMapper.selectByName("kuangxi");
        System.out.println(user);

    }

//    @Test
//    void loginTest(){
//        User user = new User();
//        user.setUsername("zhangsan");
//        user.setPassword("123");
//        boolean flag = userService.login(user);
//        System.out.println(flag);
//
//    }

    @Test
    void Code(){
        for (int i = 0; i <9 ; i++) {
            String code = OrderCodeUtil.getCode("code");
            System.out.println(code);

        }

    }

    @Test
    void test(){

    }

    @Test
    void selectOrderByUser(){
        List<Order> orders = userService.selectByUser(1, 5, 2);
        System.out.println(orders);


    }


}
