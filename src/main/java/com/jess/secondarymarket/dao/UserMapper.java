package com.jess.secondarymarket.dao;

import com.jess.secondarymarket.model.User;
import com.jess.secondarymarket.vo.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User getUserByPhone(String phoneNum);

    UserVO selectByPhoneNum(String phoneNum);
}