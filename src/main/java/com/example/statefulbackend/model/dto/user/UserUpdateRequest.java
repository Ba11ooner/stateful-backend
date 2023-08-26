package com.example.statefulbackend.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新请求
 *
 */
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;


    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户角色: user, admin
     */
    private String userRole;

    /**
     * 密码
     */
    private String userPassword;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}