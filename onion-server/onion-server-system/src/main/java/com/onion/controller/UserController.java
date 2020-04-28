package com.onion.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.onion.entity.SystemUser;
import com.onion.exception.OnionException;
import com.onion.service.UserService;
import com.onion.utils.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.Principal;

/**
 * @author gyc
 * @date 2020/4/17
 */
@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:view')")
    public ResultDto userList(Integer pageNo, Integer pageSize, SystemUser user) {
        IPage<SystemUser> usersDetail = userService.findUsersDetail(pageNo, pageSize, user);
        return ResultDto.success(usersDetail);
    }


    @GetMapping("currentUser")
    public Principal currentUser(Principal principal) {
        return principal;
    }


    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add')")
    public ResultDto addUser(@Valid SystemUser user) throws OnionException {
        try {
            this.userService.createUser(user);
            return ResultDto.success("新增成功");

        } catch (Exception e) {
            String message = "新增用户失败";
            log.error(message, e);
            throw new OnionException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('user:update')")
    public ResultDto updateUser(@Valid SystemUser user) throws OnionException {
        try {
            this.userService.updateUser(user);
            return ResultDto.success("修改成功");

        } catch (Exception e) {
            String message = "修改用户失败";
            log.error(message, e);
            throw new OnionException(message);
        }
    }

    @DeleteMapping("/{userIds}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResultDto deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) throws OnionException {
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            this.userService.deleteUsers(ids);
            return ResultDto.success("删除成功");
        } catch (Exception e) {
            String message = "删除用户失败";
            log.error(message, e);
            throw new OnionException(message);
        }
    }
}