package com.onion.utils;

import lombok.Data;

/**
 * Date:2018/9/29
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Data
public class ResultDto<T> {
    private Integer status;
    private String msg;
    private Integer code;
    private T data;


    public static <T> ResultDto success(T data) {
        ResultDto resultDto = new ResultDto();
        resultDto.setData(data);
        resultDto.setMsg("成功");
        resultDto.setCode(0);
        resultDto.setStatus(200);
        return resultDto;
    }


    public static <T> ResultDto failure(String msg) {
        ResultDto resultDto = new ResultDto();
        resultDto.setMsg(msg);
        resultDto.setCode(1);
        resultDto.setStatus(500);
        return resultDto;
    }
}