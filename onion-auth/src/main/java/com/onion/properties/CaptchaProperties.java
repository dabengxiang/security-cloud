package com.onion.properties;

import lombok.Data;

/**
 * @author gyc
 * @date 2020/4/6
 */
@Data
public class CaptchaProperties {


    //多少位验证码
    private int length = 4;

    //宽度
    private int width = 130;

    //高度
    private int height = 48 ;


    //验证码类型
    //支持png,gif
    private String type = "png";



    //验证码字符类型
//    TYPE_DEFAULT = 1;    数字和字母混合
//    TYPE_ONLY_NUMBER = 2;    纯数字
//    TYPE_ONLY_CHAR = 3;    纯字母
//    TYPE_ONLY_UPPER = 4;    纯大写字母
//    TYPE_ONLY_LOWER = 5;    纯小写字母
//    TYPE_NUM_AND_UPPER = 6;	数字和大写字母
    private int charType = 2;



    //过期时间
    private long expire = 60;

    /**
     * 存储类型
     * session
     * redis
     */
    private String storeType;

}


