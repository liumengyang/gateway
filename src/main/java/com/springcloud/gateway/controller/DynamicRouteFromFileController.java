package com.springcloud.gateway.controller;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.gateway.properties.RouteDefines;
import com.springcloud.gateway.service.DynamicRouteService;
import com.springcloud.gateway.service.DynamicRoutesFromFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URISyntaxException;
import java.util.Map;

@Controller
@RequestMapping(value = "route/file/")
public class DynamicRouteFromFileController {
    @Autowired
    private DynamicRouteService routeService;
    @Autowired
    private DynamicRoutesFromFileService fileRouteService;
    @Autowired
    private RouteDefines routeDefines;

    @GetMapping(value = "add")
    @ResponseBody
    public String addRoute() throws URISyntaxException {
        Map<String, String> methods = routeDefines.getMethods();
        methods.values().stream().forEach(x -> {
            try {
                System.out.println("配置文件读取的信息" + x);
                JSONObject jsonObject = JSONObject.parseObject(x);
                //组装RouteDefinition
                RouteDefinition routeDefinition = this.fileRouteService.getRouteDefinition(jsonObject);
                //路由信息写入
                String save = routeService.add(routeDefinition);
            } catch (Exception e) {
                //logger.error("[路由初始化] 异常", e);
            }
        });
        return "add route success from file";
    }
}
