package com.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.BlockResponse;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackProvider;
import com.alibaba.csp.sentinel.log.RecordLog;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @Author: gyc
 * @Date: 2020/4/13 8:48
 */
public class MyBlockFallbackProvider implements ZuulBlockFallbackProvider {
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public BlockResponse fallbackResponse(String route, Throwable throwable) {
        RecordLog.info(String.format("[Sentinel DefaultBlockFallbackProvider] Run fallback route: %s", route));
        if (throwable instanceof BlockException) {
            return new BlockResponse(429, "刷新太频繁", route);
        } else {
            return new BlockResponse(500, "系统错误", route);
        }

    }



}
