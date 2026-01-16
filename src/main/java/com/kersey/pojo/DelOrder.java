package com.kersey.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DelOrder {
    private String orderId;

    private int    userId;
    private int currierId;
}
