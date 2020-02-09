package com.heylhh.user.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name="time_course")
@ApiModel(description="控制器类返回结果")
public class Course {
    @Id
    @ApiModelProperty(value="课程id",required = false)
    private String courseId;
    @ApiModelProperty(value="任课用户id",required = false)
    private String userId;
    @ApiModelProperty(value="所属专业id",required = false)
    private String majorId;
    @ApiModelProperty(value="课程代码",required = false)
    private String courseCode;
    @ApiModelProperty(value="所属专业名称",required = false)
    private String majorName;
    @ApiModelProperty(value="任课用户姓名",required = false)
    private String userName;
    @ApiModelProperty(value="课程名称",required = false)
    private String courseName;
    @ApiModelProperty(value="课程开始时间",required = false)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date courseStart;
    @ApiModelProperty(value="课程结束时间",required = false)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date courseEnd;
    @ApiModelProperty(value="签到码",required = false)
    private String attendanceCode;
    @ApiModelProperty(value="上课地址",required = false)
    private String courseAddr;


    }
