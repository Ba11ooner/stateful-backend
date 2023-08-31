package com.example.mapper;

import com.example.model.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Ba11ooner
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-08-24 11:12:17
* @Entity com.example.statefulbackend.model.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




