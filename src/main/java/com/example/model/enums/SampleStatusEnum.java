package com.example.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 样例状态枚举
 */
public enum SampleStatusEnum {

    STATUS_01("状态1",0),
    STATUS_02("状态2",1);

    private final String text;

    private final int value;

    SampleStatusEnum(String text, int value){
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
