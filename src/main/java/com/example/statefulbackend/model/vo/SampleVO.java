package com.example.statefulbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 样例视图
 *
 * @TableName sample
 */
@Data
public class SampleVO implements Serializable {
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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
