package com.onion.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: gyc
 * @Date: 2020/3/19 10:49
 */

public class OnionUtils {


    /**
     *
     * @param response
     * @param contextType  返回格式
     * @param status          状态码
     * @param value       信息
     */
    public static  void makeResponse(HttpServletResponse response,String contextType,int  status ,Object value) throws IOException {

        response.setContentType(contextType);
        response.setStatus(status );
        response.getOutputStream().write(JSONObject.toJSONBytes(value));

    }

}
