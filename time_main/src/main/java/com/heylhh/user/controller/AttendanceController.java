package com.heylhh.user.controller;

import com.heylhh.common.entity.Result;
import com.heylhh.user.pojo.*;
import com.heylhh.user.service.AttendanceService;
import com.heylhh.user.service.CourseService;
import com.heylhh.user.service.UserService;
import com.heylhh.user.util.UserInfoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/attendance")
@Api(value = "用户签到API", tags = {"用户签到API"})
public class AttendanceController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    @ApiOperation(value = "新增签到信息", notes = "新增签到信息")
    public Result addCourse(@RequestBody Attendance attendance) {
        User userInfo = (User) request.getSession().getAttribute("userInfo");
        if (userInfo == null){
            userInfo = UserInfoUtil.getUserInfo(request);
            userInfo = userService.findByUserNameAndUserType(userInfo);
        }
        attendance.setUserId(userInfo.getUserId());
        attendance.setUserName(userInfo.getUserName());
        Course course = courseService.findById(attendance.getCourseId());
        attendance.setCourseName(course.getCourseName());
        attendance.setCourseCode(course.getCourseCode());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        attendance.setAttendanceTime(sdf.format(new Date()));
        Attendance add = attendanceService.addAttendance(attendance);
        if (add != null) {
            return Result.getListSuccess("新增签到信息成功！");
        } else {
            return Result.getListError("新增签到信息失败");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "获取当前用户的签到信息（带分页）", notes = "获取当前用户的签到信息")
    public Result getList(@RequestBody PageParam pageParam) {
        User userInfo = (User) request.getSession().getAttribute("userInfo");
        if (userInfo == null){
            userInfo = UserInfoUtil.getUserInfo(request);
            userInfo = userService.findByUserNameAndUserType(userInfo);
        }
        Attendance attendance = new Attendance();
        attendance.setUserId(userInfo.getUserId());
        Page<Attendance> search = attendanceService.findSearch(attendance, pageParam.getPage(), pageParam.getLimit());
        if (search != null) {
            return Result.getListSuccess(search.getContent(),search.getTotalPages(),"获取签到信息成功！");
        } else {
            return Result.getListError("获取签到信息失败");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "获取当前用户的签到信息（不带分页）", notes = "获取当前用户的签到信息")
    public Result getList() {
        User userInfo = (User) request.getSession().getAttribute("userInfo");
        if (userInfo == null){
            userInfo = UserInfoUtil.getUserInfo(request);
            userInfo = userService.findByUserNameAndUserType(userInfo);
        }
        Attendance attendance = new Attendance();
        attendance.setUserId(userInfo.getUserId());
        List<Attendance> search = attendanceService.findSearch(attendance);
        if (search != null) {
            return Result.getListSuccess(search,"获取签到信息成功！");
        } else {
            return Result.getListError("获取签到信息失败");
        }
    }

    @RequestMapping(value = "/updateAttendance", method = RequestMethod.GET)
    @ApiOperation(value = "根据签到id更新签到信息", notes = "更新签到信息")
    public Result updateAttendance(@RequestBody Attendance attendance) {
        Attendance updateAttendance = attendanceService.updateAttendance(attendance);
        if (updateAttendance != null) {
            return Result.getListSuccess(updateAttendance,"更新签到信息成功！");
        } else {
            return Result.getListError("更新签到信息失败");
        }
    }
}
