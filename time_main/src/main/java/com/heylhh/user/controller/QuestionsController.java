package com.heylhh.user.controller;

import com.heylhh.common.entity.Result;
import com.heylhh.user.pojo.Attendance;
import com.heylhh.user.pojo.Course;
import com.heylhh.user.pojo.Questions;
import com.heylhh.user.pojo.User;
import com.heylhh.user.service.QuestionsService;
import com.heylhh.user.util.UserInfoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
@Api(value = "随堂提问模块API", tags = {"随堂提问模块API"})
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;


    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    @ApiOperation(value = "根据课程id获取随堂提问信息", notes = "根据课程id获取随堂提问信息")
    public Result addCourse(@RequestBody Questions questions) {
        List<Questions> questionsList = questionsService.findSearch(questions);
        if (questionsList != null) {
            return Result.getListSuccess(questionsList,"获取随堂提问信息成功！");
        } else {
            return Result.getListError("获取随堂提问信息失败");
        }
    }
}
