package com.kersey.service;

import com.kersey.pojo.ChartData;
import com.kersey.pojo.RoleData;

import java.util.List;

public interface ChartService {

    public List<ChartData> getChartData();


    public List<ChartData> getChartOrder();

    public List<RoleData> getChartRole();
//    public int initChartData();
//
//    public int updateChartData();
}
