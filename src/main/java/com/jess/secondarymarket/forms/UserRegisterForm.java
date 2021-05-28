package com.jess.secondarymarket.forms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: secondarymarket
 * @description: TODO
 * @author: Jess
 * @create: 2021-05-27 08:11
 **/
@Data
public class UserRegisterForm {

    @NotNull(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String userName;

    @NotNull(message = "手机号不能为空")
    @ApiModelProperty("手机号码")
    private String userPhone;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty("登录密码")
    private String userPwd;

    @NotNull(message = "学号不能为空")
    @ApiModelProperty("学号")
    private Long userNum;

    @NotNull(message = "验证码不能为空")
    @ApiModelProperty("验证码")
    private String verifyCode;
}
