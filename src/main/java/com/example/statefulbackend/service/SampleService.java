package com.example.statefulbackend.service;

import com.example.statefulbackend.model.domain.Sample;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Ba11ooner
* @description 针对表【sample(样例)】的数据库操作Service
* @createDate 2023-08-24 11:12:17
*/
public interface SampleService extends IService<Sample> {

    String helloSample();

    void validSample(Sample sample, boolean isAdd);
}
