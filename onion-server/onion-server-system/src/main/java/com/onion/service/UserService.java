package com.onion.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onion.entity.SystemUser;
import com.onion.entity.UserRole;
import com.onion.mapper.UserMapper;
import com.onion.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author gyc
 * @date 2020/4/17
 */

@Service
public class UserService extends ServiceImpl<UserMapper, SystemUser> {


    @Autowired
    private UserMapper userMapper;


    @Autowired
    private PasswordEncoder passwordEncoder ;


    @Autowired
    private UserRoleService userRoleService;

    /**
     * 查询
     * @param pageNo
     * @param pageSize
     * @param systemUsers
     * @return
     */
    public IPage<SystemUser>  findUsersDetail(Integer pageNo, Integer pageSize, SystemUser systemUsers){
        if(pageNo == null){
            pageNo = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        Page page = new Page(pageNo,pageSize);
        return  userMapper.findUsersDetail(page, systemUsers);

    }



    @Transactional
    public void createUser(SystemUser user) {
        // 创建用户
        user.setCreateTime(new Date());
        user.setAvatar(SystemUser.DEFAULT_AVATAR);
        user.setPassword(passwordEncoder.encode(SystemUser.DEFAULT_PASSWORD));
        save(user);
        // 保存用户角色
        if(user.getRoleId()!=null){

            String[] roles = user.getRoleId().split(StringPool.COMMA);
            setUserRoles(user, roles);
        }

    }

    @Transactional
    public void updateUser(SystemUser user) {
        // 更新用户
        user.setPassword(null);
        user.setUsername(null);
        user.setCreateTime(null);
        user.setModifyTime(new Date());
        updateById(user);

        userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getUserId()));
        String[] roles = user.getRoleId().split(StringPool.COMMA);
        setUserRoles(user, roles);
    }

    @Transactional
    public void deleteUsers(String[] userIds) {
        List<String> list = Arrays.asList(userIds);
        removeByIds(list);
        // 删除用户角色
        this.userRoleService.deleteUserRolesByUserId(userIds);
    }

    private void setUserRoles(SystemUser user, String[] roles) {
        Arrays.stream(roles).forEach(roleId -> {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(Long.valueOf(roleId));
            userRoleService.save(ur);
        });
    }


}
