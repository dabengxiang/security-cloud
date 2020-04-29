package com.onion.service;

import com.onion.entity.LoginLog;
import com.onion.mapper.LoginMapper;
import com.onion.utils.AddressUtil;
import com.onion.utils.OnionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author gyc
 * @date 2020/4/28
 */
@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public void saveLoginLog(LoginLog loginLog) {
        loginLog.setLoginTime(new Date());
        String ip = OnionUtils.getHttpServletRequestIpAddress();
        loginLog.setIp(ip);
        loginLog.setLocation(AddressUtil.getCityInfo(ip));
        loginMapper.insert(loginLog);
    }
}
