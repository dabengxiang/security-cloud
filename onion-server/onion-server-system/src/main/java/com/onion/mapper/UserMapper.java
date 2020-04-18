package com.onion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.onion.entity.SystemUser;
import feign.Param;

/**
 * @Author: gyc
 * @Date: 2020/4/16 8:16
 */
public interface UserMapper extends BaseMapper<SystemUser> {

     IPage<SystemUser> findUsersDetail(Page page, @Param("systemUser") SystemUser systemUser);
}
