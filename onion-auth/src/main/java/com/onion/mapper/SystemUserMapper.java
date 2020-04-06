package com.onion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onion.entity.SystemUser;
import feign.Param;

/**
 * @author gyc
 * @date 2020/3/31
 */

public interface SystemUserMapper extends BaseMapper<SystemUser> {

    public SystemUser findByUsername(@Param("userName") String userName);
}
