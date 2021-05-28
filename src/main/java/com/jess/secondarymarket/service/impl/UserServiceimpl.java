package com.jess.secondarymarket.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.jess.secondarymarket.dao.GoodsMapper;
import com.jess.secondarymarket.dao.PublishMapper;
import com.jess.secondarymarket.dao.UserMapper;
import com.jess.secondarymarket.enums.ResultEnum;
import com.jess.secondarymarket.forms.LoginForm;
import com.jess.secondarymarket.forms.ModifyPasswordForm;
import com.jess.secondarymarket.forms.UserRegisterForm;
import com.jess.secondarymarket.model.Goods;
import com.jess.secondarymarket.model.Publish;
import com.jess.secondarymarket.model.User;
import com.jess.secondarymarket.security.JwtProperties;
import com.jess.secondarymarket.security.JwtUserDetailServiceImpl;
import com.jess.secondarymarket.service.UserService;
import com.jess.secondarymarket.util.AliyunMessageUtil;
import com.jess.secondarymarket.util.JwtTokenUtil;
import com.jess.secondarymarket.util.RedisUtil;
import com.jess.secondarymarket.util.ResultVOUtil;
import com.jess.secondarymarket.vo.GoodsInfoVO;
import com.jess.secondarymarket.vo.ResultVO;
import com.jess.secondarymarket.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jess.secondarymarket.util.AliyunMessageUtil.sendSms;

/**
 * @program: secondarymarket
 * @description: 用户信息管理
 * @author: Jess
 * @create: 2021-05-27 07:44
 **/

@Service
@Slf4j
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtUserDetailServiceImpl jwtUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PublishMapper publishMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public boolean insert(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User selectByUserId(Long userId) {

        return null;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String phoneNum = authentication.getName();
        String key = "anonymousUser";
        if (authentication != null && !phoneNum.equals(key)) {
            return getUserByPhoneNum(phoneNum);
        }
        return null;
    }

    @Override
    public ResultVO login(LoginForm loginForm, HttpServletResponse response) {
        User user = userMapper.getUserByPhone(loginForm.getUserPhone());
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(loginForm.getUserPhone());

        //这是加密判断 你存的明文
        if (!new BCryptPasswordEncoder().matches(loginForm.getUserPwd(), userDetails.getPassword())) {
            return ResultVOUtil.error(ResultEnum.PASSWORD_ERROR);
        }

        Authentication token = new UsernamePasswordAuthenticationToken(loginForm.getUserPhone(), loginForm.getUserPwd(), userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String realToken = jwtTokenUtil.generateToken(userDetails);
        response.addHeader(jwtProperties.getTokenName(), realToken);
        Map map = new HashMap();
        map.put("username",user.getUserName());
        map.put("role", user.getUserRole());
        map.put("token", realToken);
        return ResultVOUtil.success(map);
    }

    @Override
    public ResultVO insertUser(UserRegisterForm userRegisterForm) {
        //判断用户是否存在
        if (userMapper.selectByPhoneNum(userRegisterForm.getUserPhone()) != null){
            return ResultVOUtil.error(ResultEnum.USER_HAS_EXIST);
        }

        //校验验证码
        String code2 = (String) redisUtil.get(userRegisterForm.getUserPhone());
        log.info("login验证码为："+code2);
        if(code2 == null){
            return ResultVOUtil.error(ResultEnum.CODE_NOT_EXIST);
        }
        String code = userRegisterForm.getVerifyCode();

        //若验证码正确
        if(code.equals(code2)) {
            redisUtil.del(userRegisterForm.getUserPhone());
            User user = new User();
            BeanUtils.copyProperties(userRegisterForm, user);
            //开放注册都注册普通用户吧 写死 或者写两个注册 一个只对管理员开放 可以指定注册账户的role
            user.setUserRole(1);
            user.setUserCredit(100);
            //这加密
            user.setUserPwd(passwordEncoder.encode(userRegisterForm.getUserPwd()));
            log.info("用户信息" + user);
            int result = userMapper.insert(user);
            if (result != 1) {
                return ResultVOUtil.error(ResultEnum.USER_NOT_INSERT);
            }
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.CODE_ERROR);
    }


    @Override
    public User getUserByPhoneNum(String phoneNumber) {
        return userMapper.getUserByPhone(phoneNumber);
    }

    @Override
    public ResultVO getVertifyCode(String phoneNumber) throws ClientException {

/*        String jsonContent = "{\"number\":\"" + redisUtil.get(phoneNumber) + "\"}";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("phoneNumber", phoneNumber);
        paramMap.put("msgSign", "*******");
        paramMap.put("templateCode", "\n" + "*******");
        paramMap.put("jsonContent", jsonContent);
        SendSmsResponse sendSmsResponse = AliyunMessageUtil.sendSms(paramMap);
        if(!(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK"))) {
            if(sendSmsResponse.getCode() == null) {
                //这里可以抛出自定义异常
                return ResultVOUtil.error(ResultEnum.PHONE_CODE_SEND_ERROR);
            }

            if(!sendSmsResponse.getCode().equals("OK")) {
                //这里可以抛出自定义异常
                return ResultVOUtil.success();
            }
            log.info("短信发送失败");
        }
        log.info("短信发送成功");
        //return ResultVOUtil.error(ResultEnum.PHONE_CODE_SEND_ERROR);
       return ResultVOUtil.success();*/

        SendSmsResponse response =sendSms(phoneNumber,(String) redisUtil.get(phoneNumber));
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO updatePassword(ModifyPasswordForm modifyPasswordForm) {
        User user = getCurrentUser();
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN);
        }

        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(modifyPasswordForm.getUserPhone());
        if(!new BCryptPasswordEncoder().matches(modifyPasswordForm.getOldPassword(),userDetails.getPassword())){
            return ResultVOUtil.error(ResultEnum.OLDPASSWORD_IS_ERRER);
        }
        // user.setPassword(modifyPasswordForm.getNewPassword());
        user.setUserPwd(passwordEncoder.encode(modifyPasswordForm.getNewPassword()));
        if (update(user)) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO getOwnerMsg() {
        User user = getCurrentUser();
        UserVO result = userMapper.selectByPhoneNum(user.getUserPhone());
        if (result != null) {
            return ResultVOUtil.success(result);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO getPulishMsg() {
        User user = getCurrentUser();
        List<GoodsInfoVO> goodsInfoVOList = goodsMapper.selectByUserId(user.getId());
        return ResultVOUtil.success(goodsInfoVOList);
    }
}


