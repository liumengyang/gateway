package com.springcloud.gateway.service;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.gateway.entity.RouteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicRoutesFromDatabaseService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DynamicRoutesFromFileService fileRouteService;
    @Autowired
    private DynamicRouteService routeService;

    public void updateRoute() {
        RowMapper<RouteInfo> rowMapper = new BeanPropertyRowMapper<RouteInfo>(RouteInfo.class);
        List<RouteInfo> routeList = this.jdbcTemplate.query("select * from gateway_route", rowMapper);
        System.out.println(routeList.get(0).getDetailinfo());
        routeList.stream().forEach(x -> {
            try {
                System.out.println("配置文件读取的信息" + x);
                JSONObject jsonObject = JSONObject.parseObject(x.getDetailinfo());
                //组装RouteDefinition
                RouteDefinition routeDefinition = this.fileRouteService.getRouteDefinition(jsonObject);
                //路由信息写入
                String save = routeService.add(routeDefinition);
            } catch (Exception e) {
                //logger.error("[路由初始化] 异常", e);
            }
        });

    }

}
