package com.onion.handle;

import com.onion.utils.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

/**
 * @Author: gyc
 * @Date: 2020/3/19 15:19
 */
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultDto handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return ResultDto.failure("系统内部异常");
    }

//    @ExceptionHandler(value = FebsAuthException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResultDto handleFebsAuthException(FebsAuthException e) {
//        log.error("系统错误", e);
//        return  ResultDto.failure(e.getMessage());
//    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultDto handleAccessDeniedException(){
        return ResultDto.failure("没有权限访问该资源");
    }
}