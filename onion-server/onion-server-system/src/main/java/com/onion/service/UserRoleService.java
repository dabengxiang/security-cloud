package com.onion.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onion.entity.UserRole;
import com.onion.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @author gyc
 * @date 2020/4/17
 */
@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {

    @Transactional
    public void deleteUserRolesByRoleId(String[] roleIds) {
        Arrays.stream(roleIds).forEach(id -> baseMapper.deleteByRoleId(Long.valueOf(id)));
    }

    @Transactional
    public void deleteUserRolesByUserId(String[] userIds) {
        Arrays.stream(userIds).forEach(id -> baseMapper.deleteByUserId(Long.valueOf(id)));
    }
}
