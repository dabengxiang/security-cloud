package com.onion.service.validateCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gyc
 * @date 2020/4/6
 */

public interface ValidateCodeStoreService {

    public void store(HttpServletRequest request, HttpServletResponse response,ValidateCode validateCode);

    public boolean validate(HttpServletRequest request, HttpServletResponse response,String key);
}
