package com.onion.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.onion.entity.SystemUser;
import com.onion.mapper.UserMapper;
import com.onion.system.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: gyc
 * @Date: 2020/4/16 8:24
 */
public class UserMapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void selectTest(){


        QueryWrapper<SystemUser> systemUserQueryWrapper = new QueryWrapper<>();
        systemUserQueryWrapper.eq("username","onion");
        List<SystemUser> systemUsers = userMapper.selectList(systemUserQueryWrapper);

        System.out.println(systemUsers);
    }



    @Test
    public void findUsersDetail(){
        Page page = new Page(1,10);
        IPage<SystemUser> usersDetail = userMapper.findUsersDetail(page, null);

        System.out.println(usersDetail);
    }
}
