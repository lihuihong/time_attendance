package com.heylhh.user.controller;

import com.heylhh.common.entity.Result;
import com.heylhh.user.pojo.PageParam;
import com.heylhh.user.pojo.Major;
import com.heylhh.user.service.MajorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/major")
@Api(value = "专业模块API", tags = {"专业模块API"})
public class MajorController {

    @Autowired
    private MajorService majorService;

    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    @ApiOperation(value = "获取专业信息(不带分页)", notes = "获取专业信息")
    public Result getList(){
        List<Major> majorList = majorService.findAll();
        return Result.getListSuccess(majorList,"请求成功！");
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ApiOperation(value = "获取专业信息(带分页)", notes = "获取专业信息")
    public Result getListPage(@RequestBody PageParam pageParam){
        Major majorInfo = new Major();
        majorInfo.setMajorName(pageParam.getKey());
        Page<Major> search = majorService.findSearch(majorInfo, pageParam.getPage(), pageParam.getLimit());
        return Result.getListSuccess(search.getContent(),search.getTotalPages(),"请求成功！");
    }

    @RequestMapping(value = "/addMajor", method = RequestMethod.POST)
    @ApiOperation(value = "新增专业信息", notes = "新增专业信息")
    public Result addMajor(@RequestBody Major major) {
        Major add = majorService.addMajor(major);
        if (add != null) {
            return Result.getListSuccess("新增专业信息成功！");
        } else {
            return Result.getListError("新增专业信息失败");
        }
    }

    @RequestMapping(value = "/updateMajor", method = RequestMethod.POST)
    @ApiOperation(value = "编辑专业信息", notes = "编辑专业信息")
    public Result updateMajor(@RequestBody Major major) {
        Major add = majorService.updateMajor(major);
        if (add != null) {
            return Result.getListSuccess("编辑专业信息成功！");
        } else {
            return Result.getListError("编辑专业信息失败");
        }
    }

    @RequestMapping(value = "/deleteMajorById", method = RequestMethod.POST)
    @ApiOperation(value = "删除专业信息", notes = "删除专业信息")
    public Result deleteMajorById(
            @ApiParam(name = "majorId", value = "专业id", required = false)
            @RequestParam(value = "majorId") String majorId) {
        majorService.deleteMajorById(majorId);
        return Result.getListSuccess("删除专业信息成功！");
    }


}
