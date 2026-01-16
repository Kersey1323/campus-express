package com.kersey.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class OrderCodeUtil {




    public static String getCode(String key) {


        StringBuilder orderNo = new StringBuilder();
        String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
        orderNo.append("SE").append(format);
        Jedis jedis = new Jedis();
        long increment = jedis.llen(key);


        if (increment == 0) {
//            当数据为0时，设置有效期，10秒后过期

            jedis.expire(key, 10);
        }
        increment = increment+1;
        String count = increment <= 9 ? "00000" : increment <= 99 ? "0000" : increment <= 999 ? "000" : increment <= 9999 ? "00" : increment <= 99999 ? "0" : "";
        orderNo.append(count).append(increment);
        jedis.rpush(key, orderNo.toString());
        return orderNo.toString();
    }

    /*
     * 获取次日凌晨的时间戳
     * */

    public static long getNextDay() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
//        今天凌晨
        return c.getTimeInMillis() / 1000 * (24 * 60 * 60);


    }


}
