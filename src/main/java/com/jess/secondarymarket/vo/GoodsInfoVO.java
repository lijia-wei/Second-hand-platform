package com.jess.secondarymarket.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: secondarymarket
 * @description: 商品详情
 * @author: Jess
 * @create: 2021-05-27 23:00
 **/
@Data
public class GoodsInfoVO {

    private String goodsName;

    private BigDecimal goodsPrice;

    private Integer goodsNum;

    private String goodsLevel;

    private String goodsDue;

    private Long goodsRead;

    private Long goodsLike;

    private Integer goodsStatus;

    private String goodsComment;

    private String createTime;

    private String updateTime;

    private String goodsIntro;
}
