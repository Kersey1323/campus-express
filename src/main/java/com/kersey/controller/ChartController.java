package com.kersey.controller;

import com.kersey.pojo.ChartData;
import com.kersey.pojo.RoleData;
import com.kersey.service.ChartService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    ChartService chartService;

    @GetMapping
    public List<ChartData> getChartData() {
        return chartService.getChartData();
    }

    @GetMapping("/order")
    public List<ChartData> getChartOrder() {

        return chartService.getChartOrder();
    }

    @GetMapping("/role")
    public List<RoleData> getChartRole() {

        return chartService.getChartRole();
    }
}

