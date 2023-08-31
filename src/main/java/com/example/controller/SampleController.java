package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.example.model.domain.Sample;
import com.example.common.BaseResponse;
import com.example.common.ErrorCode;
import com.example.common.IdRequest;
import com.example.common.ResultUtils;
import com.example.exception.BusinessException;
import com.example.model.dto.sample.SampleAddRequest;
import com.example.model.dto.sample.SampleQueryRequest;
import com.example.model.dto.sample.SampleUpdateRequest;
import com.example.model.vo.SampleVO;
import com.example.service.SampleService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @Resource
    SampleService sampleService;

    /**
     * 请求判空
     *
     * @param requestBody
     */
    private void isEmpty(Object requestBody) {
        if (requestBody == null) {
            throw new BusinessException(ErrorCode.REQUEST_NULL);
        }
    }

    //region 增删改查

    /**
     * 增加样例
     * 要求给 Id，因为只有这一项作为区分项
     * 若希望 id 自动生成，则至少提供另一个用于区分的属性
     * 若只用 id 作为区分属性，则必须手动指定 id
     *
     * @param sampleAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addSample(@RequestBody SampleAddRequest sampleAddRequest, HttpServletRequest request) {
        //请求判空
        isEmpty(sampleAddRequest);
        //数据处理
        Sample sample = new Sample();
        BeanUtils.copyProperties(sampleAddRequest, sample);
        //服务调用：原样保存
        boolean result = sampleService.save(sample);
        if (!result) { //样例已存在
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(sample.getId());
    }

    /**
     * 删除样例
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteSample(@RequestBody IdRequest deleteRequest, HttpServletRequest request) {
        //请求合法性判断
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //服务调用
        boolean result = sampleService.removeById(deleteRequest.getId());
        //返回结果
        return ResultUtils.success(result);
    }


    /**
     * 修改样例
     *
     * @param sampleUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateSample(@RequestBody SampleUpdateRequest sampleUpdateRequest, HttpServletRequest request) {
        //合法性判断
        if (sampleUpdateRequest == null || sampleUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //获取数据
        Sample sample = new Sample();
        BeanUtils.copyProperties(sampleUpdateRequest, sample);
        //服务调用
        boolean result = sampleService.updateById(sample);
        //返回结果
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取样例
     *
     * @param idRequest
     * @param request
     * @return
     */
    @PostMapping("/get")
    public BaseResponse<SampleVO> getSampleById(@RequestBody IdRequest idRequest, HttpServletRequest request) {
        //请求判空
        isEmpty(idRequest);
        //数据获取
        long id = idRequest.getId();
        //请求合法性判断
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //服务调用
        Sample sample = sampleService.getById(id);
        SampleVO sampleVO = new SampleVO();
        //返回结果
        BeanUtils.copyProperties(sample, sampleVO);
        return ResultUtils.success(sampleVO);
    }

    /**
     * 获取样例列表
     *
     * @param sampleQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<SampleVO>> list(SampleQueryRequest sampleQueryRequest, HttpServletRequest request) {
        //判空并处理
        Sample sampleQuery = new Sample();
        if (sampleQueryRequest != null) {
            BeanUtils.copyProperties(sampleQueryRequest, sampleQuery);
        }
        //服务调用
        QueryWrapper<Sample> queryWrapper = new QueryWrapper<>(sampleQuery);
        List<Sample> sampleList = sampleService.list(queryWrapper);
        List<SampleVO> sampleVOList = sampleList.stream().map(sample -> {
            SampleVO sampleVO = new SampleVO();
            BeanUtils.copyProperties(sample, sampleVO);
            return sampleVO;
        }).collect(Collectors.toList());
        //返回结果
        return ResultUtils.success(sampleVOList);
    }

    /**
     * 分页获取样例列表
     *
     * @param sampleQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<SampleVO>> listSampleByPage(SampleQueryRequest sampleQueryRequest, HttpServletRequest request) {
        //设置默认页面（从 1 开始）和默认获取数量
        long current = 1;
        long size = 10;
        //判空并处理
        Sample sampleQuery = new Sample();
        if (sampleQueryRequest != null) {
            BeanUtils.copyProperties(sampleQueryRequest, sampleQuery);
            current = sampleQueryRequest.getCurrent();
            size = sampleQueryRequest.getPageSize();
        }
        //服务调用
        //设置查询条件
        QueryWrapper<Sample> queryWrapper = new QueryWrapper<>(sampleQuery);
        //执行分页查询
        Page<Sample> samplePage = sampleService.page(new Page<>(current, size), queryWrapper);
        //处理分页查询结果
        Page<SampleVO> sampleVOPage = new PageDTO<>(samplePage.getCurrent(), samplePage.getSize(), samplePage.getTotal());
        List<SampleVO> sampleVOList = samplePage.getRecords().stream().map(sample -> {
            SampleVO sampleVO = new SampleVO();
            BeanUtils.copyProperties(sample, sampleVO);
            return sampleVO;
        }).collect(Collectors.toList());
        sampleVOPage.setRecords(sampleVOList);
        //返回结果
        return ResultUtils.success(sampleVOPage);
    }
    //endregion
}
