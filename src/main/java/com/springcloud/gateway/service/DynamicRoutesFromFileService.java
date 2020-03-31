package com.springcloud.gateway.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicRoutesFromFileService {

    public RouteDefinition getRouteDefinition(JSONObject jsonObject) throws URISyntaxException {
        RouteDefinition routeDefinition=new RouteDefinition();
        routeDefinition.setId(jsonObject.getString("id"));
        List<PredicateDefinition> predicateList = getPredicateList(jsonObject);
        routeDefinition.setPredicates(predicateList);

        List<FilterDefinition> filterDefinition1 = getFilterDefinition(jsonObject);
        routeDefinition.setFilters(filterDefinition1);

        URI uri = new URI(jsonObject.getString("uri"));
        routeDefinition.setUri(uri);
        routeDefinition.setOrder(jsonObject.getIntValue("order"));
        return routeDefinition;
    }

    /**
     * 解析json 获得PredicateList
     * @param jsonObject
     * @return
     */
    private List<PredicateDefinition> getPredicateList(JSONObject jsonObject) {
        JSONArray predicateDefinition = jsonObject.getJSONArray("predicateDefinition");
        List<PredicateDefinition> predicates=new ArrayList<>();
        predicateDefinition.stream().forEach(predicate->{
            JSONObject jsonObject1 = JSONObject.parseObject(predicate.toString());
            PredicateDefinition definition=new PredicateDefinition();
            definition.setName(jsonObject1.getString("name"));
            definition.addArg(jsonObject1.getString("predicateKey"),jsonObject1.getString("predicateValue"));
            predicates.add(definition);

        });
        return predicates;
    }

    /**
     * 解析json 获得FilterDefinitionList
     * @param jsonObject
     * @return
     */
    private List<FilterDefinition> getFilterDefinition(JSONObject jsonObject) {
        JSONArray predicateDefinition = jsonObject.getJSONArray("filterDefinition");
        List<FilterDefinition> predicates=new ArrayList<>();
        predicateDefinition.stream().forEach(predicate->{
            JSONObject jsonObject1 = JSONObject.parseObject(predicate.toString());
            FilterDefinition definition=new FilterDefinition();
            definition.setName(jsonObject1.getString("name"));
            definition.addArg(jsonObject1.getString("filterKey"),jsonObject1.getString("filterValue"));
            predicates.add(definition);

        });
        return predicates;
    }
}
