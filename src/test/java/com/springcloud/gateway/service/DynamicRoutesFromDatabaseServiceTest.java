package com.springcloud.gateway.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DynamicRoutesFromDatabaseServiceTest {
    @Autowired
    private DynamicRoutesFromDatabaseService baseService;

    @Test
    void updateRoute() {
        this.baseService.updateRoute();
    }
}