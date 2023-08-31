package com.example.model.dto.sample;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户更新请求
 *
 */
@Data
public class SampleUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 样例文本
     */
    private String sampleTest;


    /**
     * 样例状态
     */
    private Integer sampleStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}