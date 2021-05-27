package com.jess.secondarymarket.controller;

import com.jess.secondarymarket.forms.ModifyPasswordForm;
import com.jess.secondarymarket.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: secondarymarket
 * @description: 用户接口
 * @author: Jess
 * @create: 2021-05-27 08:08
 **/
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("修改密码")
    @PostMapping(name = "用户修改密码", value = "/updatePassword")
    public Object updatePassword(ModifyPasswordForm modifyPasswordForm) {
        return userService.updatePassword(modifyPasswordForm);
    }
}
