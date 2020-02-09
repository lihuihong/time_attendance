package com.heylhh.user.controller;

import com.heylhh.common.entity.Result;
import com.heylhh.user.pojo.Course;
import com.heylhh.user.pojo.Major;
import com.heylhh.user.pojo.PageParam;
import com.heylhh.user.pojo.User;
import com.heylhh.user.service.CourseService;
import com.heylhh.user.service.MajorService;
import com.heylhh.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@Api(value = "课程模块API", tags = {"课程模块API"})
public class CourseController {
    
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private MajorService majorService;

    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    @ApiOperation(value = "获取课程信息(不带分页)", notes = "获取课程信息")
    public Result getList(){
        List<Course> CourseList = courseService.findAll();
        return Result.getListSuccess(CourseList,"请求成功！");
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ApiOperation(value = "获取课程信息(带分页)", notes = "获取课程信息")
    public Result getListPage(@RequestBody PageParam pageParam){
        Course course = new Course();
        course.setCourseName(pageParam.getKey());
        Page<Course> search = courseService.findSearch(course, pageParam.getPage(), pageParam.getLimit());
        return Result.getListSuccess(search.getContent(),search.getTotalPages(),"请求成功！");
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    @ApiOperation(value = "新增课程信息", notes = "新增课程信息")
    public Result addCourse(@RequestBody Course course) {
        Major major = majorService.findById(course.getMajorId());
        course.setMajorName(major.getMajorName());
        User user = userService.findById(course.getUserId());
        course.setUserName(user.getUserName());
        Course add = courseService.addCourse(course);
        if (add != null) {
            return Result.getListSuccess("新增课程信息成功！");
        } else {
            return Result.getListError("新增课程信息失败");
        }
    }

    @RequestMapping(value = "/updateCourse", method = RequestMethod.POST)
    @ApiOperation(value = "编辑课程信息", notes = "编辑课程信息")
    public Result updateCourse(@RequestBody Course course) {
        User user = userService.findById(course.getUserId());
        Major major = majorService.findById(course.getMajorId());
        course.setMajorName(major.getMajorName());
        course.setUserName(user.getUserName());
        Course add = courseService.updateCourse(course);
        if (add != null) {
            return Result.getListSuccess("编辑课程信息成功！");
        } else {
            return Result.getListError("编辑课程信息失败");
        }
    }

    @RequestMapping(value = "/deleteCourseById", method = RequestMethod.POST)
    @ApiOperation(value = "删除课程信息", notes = "删除课程信息")
    public Result deleteCourseById(
            @ApiParam(name = "courseId", value = "课程id", required = false)
            @RequestParam(value = "courseId") String courseId) {
        courseService.deleteCourseById(courseId);
        return Result.getListSuccess("删除课程信息成功！");
    }
    
}
