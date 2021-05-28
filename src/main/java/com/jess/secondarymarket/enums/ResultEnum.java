package com.jess.secondarymarket.enums;

import lombok.Getter;

/**
 * @author Jess
 * @date 2020/7/31 10:01
 */
@Getter
public enum ResultEnum {

    USER_NOT_EXIST(1, "用户不存在"),
    PASSWORD_ERROR(2, "密码错误"),
    PHONE_CODE_ERROR(3, "手机验证码错误"),
    PHONE_CODE_SEND_ERROR(4, "手机验证码发送失败"),
    USER_HAS_EXIST(5,"此手机号码已经注册"),
    USER_NOT_INSERT(6,"添加用户失败"),
    CODE_NOT_EXIST(7,"验证码不存在"),
    CODE_ERROR(8,"验证码错误"),
    OLDPASSWORD_IS_ERRER(9,"旧密码错误"),
    USER_NOT_LOGIN(10,"用户未登陆"),
    ARTICLE_NOT_EXIST(11,"文章不存在"),
    LIKE_IS_ERROR(12,"点赞失败"),
    NOTLIKE_IS_ERROR(12,"取消点赞失败"),
    IMG_UPLOAD_ERROR(13,"图片上传失败"),
    TAG_ALREADY_EAIST(14,"标签已经存在"),
    TAG_NOT_INSERT(15,"标签添加失败"),
    TAG_NOT_EXIST(16,"标签不存在"),
    COMMENT_NOT_EXIST(17,"评论不存在"),

    AUTHENTICATION_ERROR(401, "用户认证失败,请重新登录"),
    PERMISSION_DENNY(403, "权限不足"),
    NOT_FOUND(404, "url错误,请求路径未找到"),
    SERVER_ERROR(500, "服务器未知错误:%s"),
    BIND_ERROR(511, "参数校验错误:%s"),
    REQUEST_METHOD_ERROR(550, "不支持%s的请求方式");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
