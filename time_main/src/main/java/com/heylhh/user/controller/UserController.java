package com.heylhh.user.controller;

import com.heylhh.common.entity.Result;
import com.heylhh.common.util.JwtUtil;
import com.heylhh.user.pojo.User;
import com.heylhh.user.service.UserService;
import com.heylhh.user.util.FileUtill;
import com.heylhh.user.util.UserInfoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value = "用户模块API", tags = {"用户模块API"})
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public Result login(@RequestBody User user) {
        HttpSession session = request.getSession();
        User userInfo = userService.findByLoginNameAndPassword(user.getUserName(), user.getUserPassword(), user.getUserType());
        if (userInfo != null) {
            //生成token
            String token = null;
            Map<String, Object> map = new HashMap<>();
            if ("0".equals(userInfo.getUserType())) {
                token = jwtUtil.createJWT(userInfo.getUserId(), userInfo.getUserName(), "student");
                map.put("token", token);
                map.put("role", "student");
            }
            if ("1".equals(userInfo.getUserType())) {
                token = jwtUtil.createJWT(userInfo.getUserId(), userInfo.getUserName(), "teacher");
                map.put("token", token);
                map.put("role", "teacher");
            }
            if ("2".equals(userInfo.getUserType())) {
                token = jwtUtil.createJWT(userInfo.getUserId(), userInfo.getUserName(), "admin");
                map.put("token", token);
                map.put("role", "admin");
            }
            session.setAttribute("userInfo", userInfo);
            return Result.getListSuccess(map, "登录成功");
        } else {
            return Result.getListError("用户名或密码错误");
        }
    }

    @RequestMapping(value = "/getUserListByUserType", method = RequestMethod.POST)
    @ApiOperation(value = "根据用户类型获取用户信息", notes = "根据用户类型获取用户信息")
    public Result getUserListByUserType(
            @ApiParam(name = "userType", value = "(用户类型0学生，1老师，2管理员)", required = false)
            @RequestParam(value = "userType") String userType){
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("userType",userType);
        List<User> search = userService.findSearch(hashMap);
        if (search != null){
            return Result.getListSuccess(search,"获取用户信息成功");
        }else {
            return Result.getListError("获取用户信息失败！");
        }
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    @ApiOperation(value = "根据用户id获取用户信息", notes = "根据用户id获取用户信息")
    public Result getUserById(
            @ApiParam(name = "userId", value = "用户id", required = false)
            @RequestParam(value = "userId") String userId){
        User search = userService.findById(userId);
        if (search != null){
            return Result.getListSuccess(search,"获取用户信息成功");
        }else {
            return Result.getListError("获取用户信息失败！");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public Result register(@RequestBody User user) {
        User add = userService.add(user);
        if (add != null) {
            return Result.getListError("用户名在该角色下已存在！");
        } else {
            return Result.getListSuccess("注册成功");
        }
    }

    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改密码", notes = "修改密码")
    public Result updatePwd(@ApiParam(name = "oldPassword", value = "旧密码", required = false)
                           @RequestParam(value = "oldPassword") String oldPassword,
                           @ApiParam(name = "newPassword", value = "新密码", required = false)
                           @RequestParam(value = "newPassword") String newPassword) {
        User resultUser = (User) request.getSession().getAttribute("userInfo");
        if (resultUser == null){
            resultUser = UserInfoUtil.getUserInfo(request);
        }
        resultUser.setUserPassword(oldPassword);
        User retrieve = userService.retrieve(resultUser, newPassword);
        if (retrieve != null) {
            return Result.getListSuccess("修改密码成功");
        } else {
            return Result.getListError("旧密码错误");
        }
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryUser", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public Result queryUser(HttpServletRequest request) {

        User resultUser = (User) request.getSession().getAttribute("userInfo");
        if (resultUser == null){
            User userInfo = UserInfoUtil.getUserInfo(request);
            resultUser = userService.findByUserNameAndUserType(userInfo);
        }
        if (resultUser == null) {
            return Result.getListError("访问出错");
        } else {
            return Result.getListSuccess(resultUser, "成功！");
        }
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    public Result updateUser(@RequestBody User user){
        HttpSession session = request.getSession();
        User userInfo = (User)session.getAttribute("userInfo");
        if (userInfo == null){
            User userRequest = UserInfoUtil.getUserInfo(request);
            userInfo = userService.findByUserNameAndUserType(userRequest);
        }
        user.setUserPassword(userInfo.getUserPassword());
        user.setUserType(userInfo.getUserType());
        user.setUserId(userInfo.getUserId());
        User update = userService.update(user);
        if (update !=null){
            session.setAttribute("userInfo", update);
            return Result.getListSuccess(update, "修改成功！");
        }else{
            return Result.getListError("修改失败");
        }
    }

    /**
     * 上传头像
     *
     * @param file
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @ApiOperation(value = "上传头像", notes = "message ：返回图片地址")
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadItemImg(@ApiParam(name = "file (MultipartFile类型)", value = "文件流", required = true)
                                            MultipartFile file) throws IllegalStateException, IOException {
        String picPath= ResourceUtils.getURL("classpath:").getPath()+"static/images/";
        String staticResources = picPath;
        String uploadCrocesIcon = "upload";
        String filePath = FileUtill.fileUpload(file, staticResources + uploadCrocesIcon);
        String replace = "/static/images"+filePath.replace(staticResources, "/");
        return Result.getListSuccess(replace);
    }

    /**
     * 退出登录
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ResponseBody
    public Result logout(HttpSession session){
        session.invalidate();
        return Result.getListSuccess("成功！");
    }

}

