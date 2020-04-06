package com.onion;

import com.onion.entity.SystemUser;
import com.onion.mapper.SystemUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author gyc
 * @date 2020/3/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthBaseTest {

    @Autowired
    private SystemUserMapper userMapper;

    @Test
    public void test(){
        List<SystemUser> systemUsers = userMapper.selectList(null);
        System.out.println(systemUsers);
    }


}
