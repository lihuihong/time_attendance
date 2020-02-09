package com.heylhh.user.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="time_questions")
@ApiModel(description="控制器类返回结果")
public class Questions {

    @Id
    @ApiModelProperty(value="签到id",required = false)
    private String questionsId;
    @ApiModelProperty(value="所属专业id",required = false)
    private String majorId;
    @ApiModelProperty(value="课程id",required = false)
    private String courseId;
    @ApiModelProperty(value="课程代码",required = false)
    private String courseCode;
    @ApiModelProperty(value="随堂提问题目标题",required = false)
    private String questionsTitle;
    @ApiModelProperty(value="随堂提问类型（0单选择题，1多选择题，2判断题，3简答题）",required = false)
    private String questionsType;
    @ApiModelProperty(value="课程名称",required = false)
    private String courseName;
    @ApiModelProperty(value="随堂提问题目A",required = false)
    private String questionsA;
    @ApiModelProperty(value="随堂提问题目B",required = false)
    private String questionsB;
    @ApiModelProperty(value="随堂提问题目C",required = false)
    private String questionsC;
    @ApiModelProperty(value="随堂提问题目D",required = false)
    private String questionsD;
    @ApiModelProperty(value="随堂提问简答题答案",required = false)
    private String questionsContent;
    @ApiModelProperty(value="随堂提问选择题或判断题答案",required = false)
    private String questionsAnswer;
}
