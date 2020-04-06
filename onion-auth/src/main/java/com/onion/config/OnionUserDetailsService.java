package com.onion.config;

import com.onion.entity.Menu;
import com.onion.entity.SystemUser;
import com.onion.mapper.MenuMapper;
import com.onion.mapper.SystemUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gyc
 * @date 2020/3/10
 */

@Service
public class OnionUserDetailsService implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private MenuMapper menuMapper;



    /**
     * 获取用户信息和权限信息，如果没有用户就抛异常
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


        SystemUser systemUser = systemUserMapper.findByUsername(userName);
        if(systemUser == null){
            throw new UsernameNotFoundException("找不到用户！");
        }


        String menu = this.getMenu(systemUser.getUsername());

        boolean islock = "0".equals(systemUser.getStatus())?false:true;


        OnionAuthUser onionAuthUser = new OnionAuthUser(systemUser.getUsername(), systemUser.getPassword(),
                true, true, true, islock, AuthorityUtils.commaSeparatedStringToAuthorityList(menu));



        BeanUtils.copyProperties(systemUser,onionAuthUser);

        return onionAuthUser;


    }


    public String getMenu(String userName){
        List<Menu> menuList = menuMapper.findMenuByUserName(userName);
        return  menuList.stream().map(Menu::getPerms).collect(Collectors.joining(","));



    }





}
