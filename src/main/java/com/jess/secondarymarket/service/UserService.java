package com.jess.secondarymarket.service;

import com.aliyuncs.exceptions.ClientException;
import com.jess.secondarymarket.forms.LoginForm;
import com.jess.secondarymarket.forms.ModifyPasswordForm;
import com.jess.secondarymarket.forms.UserRegisterForm;
import com.jess.secondarymarket.model.User;
import com.jess.secondarymarket.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    /**
     * 添加用户
     */
    boolean insert(User user);

    /**
     * 更新用户
     */
    boolean update(User user);

    /**
     * 根据id查询用户
     */
    User selectByUserId(Long userId);


    /**
     * 通过token解析用户
     */
    User getCurrentUser();

    /**
     * 用户登录
     */
    ResultVO login(LoginForm loginForm, HttpServletResponse response);


    /**
     * 用户注册
     */
    ResultVO insertUser(UserRegisterForm userRegisterForm);

    /**
     * 根据电话号码获取用户
     */
    User  getUserByPhoneNum(String phoneNumber);

    /**
     * 获取验证码
     */
    ResultVO getVertifyCode(String phoneNumber) throws ClientException;

    /***
     * 修改密码
     */
    ResultVO updatePassword(ModifyPasswordForm modifyPasswordForm);

    /**
     * 查看个人信息
     */
    ResultVO getOwnerMsg();

    /**
     * 查看个人所发商品
     */
    ResultVO getPulishMsg();
}
