package com.onion.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    /**
     * 正则校验
     *
     * @param regex 正则表达式字符串
     * @param value 要匹配的字符串
     * @return 正则校验结果
     */
    public static boolean match(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
