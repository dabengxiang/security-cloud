package com.gateway.filter;


import com.netflix.zuul.context.RequestContext;
import com.onion.utils.OnionUtils;
import com.onion.utils.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: gyc
 * @Date: 2020/3/19 14:29
 */
@Slf4j
@Component
public class OnionGatewayErrorFilter extends SendErrorFilter {

    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            SendErrorFilter.ExceptionHolder exception = this.findZuulException(ctx.getThrowable());


            String serviceId  = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);

            //exception.getThrowable().getMessage()为空，就取exception.getErrorCause()
            String message = StringUtils.isBlank(exception.getThrowable().getMessage()) ? exception.getErrorCause(): exception.getThrowable().getMessage();



            ResultDto resultDto = resolveExceptionMessage(message, serviceId);


            OnionUtils.makeResponse(ctx.getResponse(), MediaType.APPLICATION_JSON_UTF8.toString(),HttpServletResponse.SC_INTERNAL_SERVER_ERROR,resultDto);

            log.error(exception.getClass().toString(),exception);

        }catch (Exception e){
            log.error(e.getClass().toString(),e);
        }
        return null;

    }


    private ResultDto resolveExceptionMessage(String message, String serviceId) {
        if (StringUtils.containsIgnoreCase(message, "time out")) {
            return ResultDto.failure("请求" + serviceId + "服务超时");
        }
        if (StringUtils.containsIgnoreCase(message, "forwarding error")) {
            return ResultDto.failure(serviceId + "服务不可用");
        }
        return ResultDto.failure("Zuul请求" + serviceId + "服务异常");
    }

}
