package com.heylhh.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="time_major")
@ApiModel(description="控制器类返回结果")
public class Major {
    @Id
    @ApiModelProperty(value="专业id",required = false)
    private String majorId;
    @ApiModelProperty(value="专业名称",required = false)
    private String majorName;


}
