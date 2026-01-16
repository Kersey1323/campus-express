package com.kersey.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String checkCode;
    private String university;
    private int credit;
    private int status;
    private int role;
    private int orderSum;
    private float stars;

    public User(String username, String password) {

        this.username = username;
        this.password = password;

    }

    public User(int id,String username,int status,int role,String password,String university,int credit,int orderSum,float stars) {
        this.id=id;
        this.username = username;
        this.status=status;
        this.role=role;
        this.password=password;
        this.university=university;
        this.credit=credit;
        this.orderSum=orderSum;
        this.stars=stars;
    }
}
