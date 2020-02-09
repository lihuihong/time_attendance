package com.heylhh.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name="time_user")
@ApiModel(description="控制器类返回结果")
public class User implements Serializable {
    @Id
    @ApiModelProperty(value="userId",required = false)
    private String userId;
    @ApiModelProperty(value="(用户类型0学生，1老师，2管理员)",required = false)
    private String userType;
    @ApiModelProperty(value="学号",required = false)
    private String userCode;
    @ApiModelProperty(value="姓名",required = false)
    private String userName;
    @ApiModelProperty(value="密码",required = false)
    private String userPassword;
    @ApiModelProperty(value="头像",required = false)
    private String userPic;
    @ApiModelProperty(value="专业id",required = false)
    private String majorId;
    @ApiModelProperty(value="专业",required = false)
    private String userMajor;
    @ApiModelProperty(value="简介",required = false)
    private String userMark;

}
