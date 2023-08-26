package com.example.statefulbackend.mapper;

import com.example.statefulbackend.model.domain.Sample;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Ba11ooner
* @description 针对表【sample(样例)】的数据库操作Mapper
* @createDate 2023-08-24 11:12:17
* @Entity com.example.statefulbackend.model.domain.Sample
*/
@Mapper
public interface SampleMapper extends BaseMapper<Sample> {

}




