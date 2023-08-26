package com.example.statefulbackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求
 *
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -4137145229536222265L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 校验密码
     */
    private String checkPassword;

}
