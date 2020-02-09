package com.heylhh.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 分页结果类
 */
@Data
@AllArgsConstructor
@ApiModel(description="分页结果类返回结果")
public class PageResult <T>{
    //总条数
    @ApiModelProperty(value="总条数", required=true)
    private long total;
    //数据
    @ApiModelProperty(value="数据", required=true)
    private List<T> rows;
}
