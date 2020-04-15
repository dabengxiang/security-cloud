package com.onion.feign;

import com.onion.constant.OnionFeignConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: gyc
 * @Date: 2020/3/19 15:46
 */
@FeignClient(value = OnionFeignConstant.ONION_SERVER_SYSTEM,fallback = SystemFeignFallback.class)
public interface SystemFeign {

    @GetMapping("userRead")
    public String userRead();

}
