package com.jess.secondarymarket.vo;

import lombok.Data;

/**
 * @program: secondarymarket
 * @description: TODO
 * @author: Jess
 * @create: 2021-05-27 07:46
 **/
@Data
public class ResultVO<T> {

    private  Integer code;

    private String msg;

    private T data;
}
