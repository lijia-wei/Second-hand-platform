package com.jess.secondarymarket.vo;

import lombok.Data;

/**
 * @program: secondarymarket
 * @description: TODO
 * @author: Jess
 * @create: 2021-05-27 08:53
 **/

@Data
public class UserVO {
    private Long id;

    private String userName;

    private String userPhone;

    private Long userNum;

    private String userImage;

    private Integer userCredit;
}
