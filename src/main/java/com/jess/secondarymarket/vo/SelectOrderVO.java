package com.jess.secondarymarket.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: secondarymarket
 * @description: TODO
 * @author: Jess
 * @create: 2021-06-01 18:25
 **/
@Data
public class SelectOrderVO {

    /**
     * 卖家昵称
     */
    private String userName;

    private Integer userCredit;

    private String goodsName;

    private BigDecimal goodsPrice;

    private String goodsLevel;

    private String goodsDue;
}
