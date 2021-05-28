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
public class LoginForm {
    @NotNull(message = "手机号不能为空")
    @ApiModelProperty("手机号码")
    private String userPhone;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty("登录密码")
    private String userPwd;
}
