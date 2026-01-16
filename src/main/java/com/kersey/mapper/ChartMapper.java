package com.kersey.mapper;

import com.kersey.pojo.ChartData;
import com.kersey.pojo.RoleData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChartMapper {


    public List<ChartData> getChartData();

    public List<ChartData> getChartOrder();

    public List<RoleData> getChartRole();
}
