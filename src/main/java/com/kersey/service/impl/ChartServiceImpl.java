package com.kersey.service.impl;

import com.kersey.mapper.ChartMapper;
import com.kersey.pojo.ChartData;
import com.kersey.pojo.RoleData;
import com.kersey.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    ChartMapper chartMapper;

    @Override
    public List<ChartData> getChartData() {
        return chartMapper.getChartData();
    }

    @Override
    public List<ChartData> getChartOrder() {
        return chartMapper.getChartOrder();
    }

    @Override
    public List<RoleData> getChartRole() {
        return chartMapper.getChartRole();
    }
}
