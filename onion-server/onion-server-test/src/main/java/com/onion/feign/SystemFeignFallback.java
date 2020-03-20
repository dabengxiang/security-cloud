package com.onion.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: gyc
 * @Date: 2020/3/19 15:46
 */
@Slf4j
public class SystemFeignFallback implements FallbackFactory<SystemFeign> {


    @Override
    public SystemFeign create(Throwable throwable) {
        return new SystemFeign(){

            @Override
            public String userRead() {
                 log.error("userRead",throwable);
                 return "调用错误";
            }
        };
    }
}
