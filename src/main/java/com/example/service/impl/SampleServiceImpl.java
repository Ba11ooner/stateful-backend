package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ErrorCode;
import com.example.mapper.SampleMapper;
import com.example.model.domain.Sample;
import com.example.service.SampleService;
import com.example.exception.BusinessException;
import com.example.model.enums.SampleStatusEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author Ba11ooner
 * @description 针对表【sample(样例)】的数据库操作Service实现
 * @createDate 2023-08-24 11:12:17
 */
@Service
public class SampleServiceImpl extends ServiceImpl<SampleMapper, Sample>
        implements SampleService {

    //FIXME 测试用，检测 SampleTest 测试类能否正常运行
    @Override
    public String helloSample() {
        return "hello,sample!";
    }

    @Override
    public void validSample(Sample sample, boolean isAdd) {
        //数据判空
        if (sample == null) {
            throw new BusinessException(ErrorCode.PARAMS_NULL);
        }
        //数据获取
        String sampleTest = sample.getSampleTest();
        Integer sampleStatus = sample.getSampleStatus();
        //新增时验证
        if (isAdd) {
            if (StringUtils.isAnyBlank(sampleTest)|| ObjectUtils.anyNull(sampleStatus)) {
                throw new BusinessException(ErrorCode.PARAMS_NULL);
            }
        }
        //属性合法性验证
        if (StringUtils.isNotBlank(sampleTest) && sampleTest.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
        if (sampleStatus != null && !SampleStatusEnum.getValues().contains(sampleStatus)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "状态不符合要求");
        }
    }

}




