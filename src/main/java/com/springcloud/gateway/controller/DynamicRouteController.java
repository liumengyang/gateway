package com.springcloud.gateway.controller;

import com.springcloud.gateway.service.DynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "route")
public class DynamicRouteController {
    @Autowired
    private DynamicRouteService routeService;
    @GetMapping(value = "add")
    @ResponseBody
    public String addRoute() throws URISyntaxException {

        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId("test-id");
        List<PredicateDefinition> predicates = new ArrayList<>();
        PredicateDefinition definition = new PredicateDefinition();
        //注意name
        definition.setName("Path");
        definition.addArg("pattern", "/liumengyang/**");
        predicates.add(definition);
        routeDefinition.setPredicates(predicates);

        List<FilterDefinition> filters = new ArrayList<>();
        FilterDefinition filterDefinition = new FilterDefinition();
        //注意name
        filterDefinition.setName("StripPrefix");
        filterDefinition.addArg("parts", "1");
        filters.add(filterDefinition);
        routeDefinition.setFilters(filters);

        URI uri = new URI("https://www.jd.com/");
        routeDefinition.setUri(uri);
        routeDefinition.setOrder(0);
        String save = routeService.add(routeDefinition);
        System.out.println(save);
        return "route add success";
    }
}
