package com.wang.service.manage.Impl;

import com.wang.mapper.OrderMapper;
import com.wang.service.manage.ManageDashboardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: tengfei
 * @Create: 2023 04 26 10:50
 * @Description
 **/

@Service
public class ManageDashboardServiceImpl implements ManageDashboardService {

    @Resource
    OrderMapper orderMapper;

    @Override
    public Map<String, Object> getChartData() {
        // 过去十天销量
        List<Map> tenNums = orderMapper.getTenNums();
        // 过去十天销售额
        List<Map> tenPrices = orderMapper.getTenPrices();

        Map<String, Object> map = new HashMap<>();
        map.put("num", tenNums);
        map.put("price", tenPrices);
        return map;
    }
}
