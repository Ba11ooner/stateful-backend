package com.example;

import com.example.service.SampleService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class SampleTest {
    @Resource
    SampleService sampleService;

    @Test
    public void test() {
       String res =  sampleService.helloSample();
        System.out.println(res);
    }
}
