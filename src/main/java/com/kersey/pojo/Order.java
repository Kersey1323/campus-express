package com.kersey.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Order {

    private String orderId;

    private int    userId;
    private int currierId;
    private String sendName;
    private String sendTel;
    private String sendAddress;
    private String sendDay;
    private String sendTime;
    private float  price;
    private int    isPay;
    private int    kilo;
    private int isReceived;
    private String address2Receive;
    private int isFinish;
    private int isRemark;

    private String type;
    private String company;

    private String recName;
    private String recTel;
    private String recAddress;

    //备注
    private String message;
    //寄件方式
    private String remark;
    //是否急送
    private int    status;

    private int     hasDelete;
    private String  deleteReason;

    private String  createTime;



}
