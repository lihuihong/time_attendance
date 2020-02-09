package com.heylhh.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 控制器类返回结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="控制器类返回结果")
public class Result {
    //是否返回成功
    @ApiModelProperty(value="是否返回成功", required=true)
    private boolean flag;
    //返回码
    @ApiModelProperty(value="返回码", required=true)
    private Integer code;
    //返回信息
    @ApiModelProperty(value="返回信息", required=true)
    private String message;
    //返回数据
    @ApiModelProperty(value="返回数据", required=true)
    private Object data;
    @ApiModelProperty(value="数据条数", required=true)
    private Integer count;

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public static Result getListSuccess(String message) {
        Result resultVO = new Result();
        resultVO.setFlag(true);
        resultVO.setData(null);
        resultVO.setCode(ResponseCode.SUCCESS.getCode());
        resultVO.setMessage(message);
        return resultVO;
    }

    public static Result getListSuccess(Object object, String message) {
        Result resultVO = new Result();
        resultVO.setFlag(true);
        resultVO.setData(object);
        resultVO.setCode(ResponseCode.SUCCESS.getCode());
        resultVO.setMessage(message);
        return resultVO;
    }

    public static Result getListSuccess(Object object, Integer count,String message) {
        Result resultVO = new Result();
        resultVO.setFlag(true);
        resultVO.setData(object);
        resultVO.setCode(ResponseCode.SUCCESS.getCode());
        resultVO.setMessage(message);
        resultVO.setCount(count);
        return resultVO;
    }

    public static Result getListError(String message) {
        Result resultVO = new Result();
        resultVO.setFlag(false);
        resultVO.setData(null);
        resultVO.setCode(ResponseCode.ERROR.getCode());
        resultVO.setMessage(message);
        return resultVO;
    }

    public static Result getListAccessError() {
        Result resultVO = new Result();
        resultVO.setFlag(false);
        resultVO.setData(null);
        resultVO.setCode(ResponseCode.ACCESSERROR.getCode());
        resultVO.setMessage(ResponseCode.ACCESSERROR.getMsg());
        return resultVO;
    }
}
