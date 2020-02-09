package com.heylhh.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;

@Data
@ApiModel(description="前端页面参数")
public class PageParam {
    @ApiModelProperty(name = "page", value = "页数", required = false)
    private Integer page;
    @ApiModelProperty(name = "limit", value = "条数", required = false)
    private Integer limit;
    @ApiModelProperty(name = "key", value = "搜索字段", required = false)
    private String key;
}
