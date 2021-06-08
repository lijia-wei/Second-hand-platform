package com.jess.secondarymarket.controller;

import com.aliyuncs.exceptions.ClientException;
import com.jess.secondarymarket.forms.LoginForm;
import com.jess.secondarymarket.forms.UserRegisterForm;
import com.jess.secondarymarket.service.UserService;
import com.jess.secondarymarket.util.RedisUtil;
import com.jess.secondarymarket.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @program: secondarymarket
 * @description: 登录注册接口
 * @author: Jess
 * @create: 2021-05-27 08:09
 **/
@RestController
@RequestMapping("/api/anon")
@Slf4j
@Api(tags = "登录注册")
@CrossOrigin(origins = "*",maxAge = 3600)
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/insertUser")
    @ApiOperation("用户注册")
    public ResultVO insert(@Valid @RequestBody UserRegisterForm userRegisterForm){
        return userService.insertUser(userRegisterForm);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResultVO login(@Valid @RequestBody LoginForm loginForm, HttpServletResponse response){
        return userService.login(loginForm,response);
    }

    @PostMapping("/getCode")
    @ApiOperation("获取验证码")
    public ResultVO getVertifyCode(@RequestParam String phoneNumber ) throws ClientException {
        int num = 6;
        String randomNumStr = "";
        for(int i = 0; i < num;i ++){
            int randomNum = (int)(Math.random() * 10);
            randomNumStr += randomNum;
        }
        String randomNum = randomNumStr;
        redisUtil.set(phoneNumber, randomNum, 300);
        log.info("生成验证码："+randomNum);
        return userService.getVertifyCode(phoneNumber);
    }
}
