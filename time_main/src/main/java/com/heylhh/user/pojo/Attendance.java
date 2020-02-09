package com.heylhh.user.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name="time_attendance")
@ApiModel(description="控制器类返回结果")
public class Attendance {
    @Id
    @ApiModelProperty(value="签到id（不用传）",required = false)
    private String attendanceId;
    @ApiModelProperty(value="签到用户id（不用传）",required = false)
    private String userId;
    @ApiModelProperty(value="课程id",required = false)
    private String courseId;
    @ApiModelProperty(value="课程代码（不用传）",required = false)
    private String courseCode;
    @ApiModelProperty(value="签到状态（0正常，1迟到，2请假，3旷课）",required = false)
    private String attendanceStatus;
    @ApiModelProperty(value="签到用户姓名（不用传）",required = false)
    private String userName;
    @ApiModelProperty(value="签到课程名称",required = false)
    private String courseName;
    @ApiModelProperty(value="签到时间（不用传）",required = false)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private String attendanceTime;
    @ApiModelProperty(value="签到码",required = false)
    private String attendanceCode;
    @ApiModelProperty(value="签到地址",required = false)
    private String attendanceAddr;


    }
