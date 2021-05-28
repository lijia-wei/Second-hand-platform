package com.jess.secondarymarket.security;

import com.jess.secondarymarket.enums.RoleEnum;
import com.jess.secondarymarket.model.User;
import com.jess.secondarymarket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Jess
 * @date 2020/7/31 10:31
 */
@Service
@Slf4j
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String phoneNum) throws UsernameNotFoundException {
        User user = userService.getUserByPhoneNum(phoneNum);
        if (user == null) {
            log.info("此用户不存在");
            throw new UsernameNotFoundException(String.format("用户名为 %s 的用户不存在", phoneNum));
        } else {
            String role = RoleEnum.getRole(user.getUserRole());
            return new JwtUser(phoneNum, user.getUserPwd(), role);
        }
    }
}

