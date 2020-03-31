package com.springcloud.gateway.controller;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.gateway.service.DynamicRoutesFromDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URISyntaxException;
import java.util.Map;

@Controller
@RequestMapping(value = "route/db/")
public class DynamicRouteFromDatabaseController {
    @Autowired
    private DynamicRoutesFromDatabaseService routeService;
    @GetMapping(value = "add")
    @ResponseBody
    public String addRoute() throws URISyntaxException {
        this.routeService.updateRoute();
        return "add route success from db";
    }
}
