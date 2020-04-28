package com.onion.service;

import com.onion.entity.LoginLog;
import com.onion.utils.OnionUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author gyc
 * @date 2020/4/28
 */
@Service
public class LoginService {

    public void saveLoginLog(LoginLog loginLog) {
        loginLog.setLoginTime(new Date());
        String ip = OnionUtils.getHttpServletRequestIpAddress();
        loginLog.setIp(ip);
        loginLog.setLocation(AddressUtil.getCityInfo(ip));
        this.save(loginLog);
    }
}
