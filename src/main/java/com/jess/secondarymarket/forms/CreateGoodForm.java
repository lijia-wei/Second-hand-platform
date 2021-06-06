package com.jess.secondarymarket.forms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @program: secondarymarket
 * @description: 商品创建表单
 * @author: Jess
 * @create: 2021-05-27 22:17
 **/
@Data
public class CreateGoodForm {

    @NotNull(message = "标签不能为空")
    @ApiModelProperty("标签id")
    private Integer tagsId;

    @NotNull(message = "商品名不能为空")
    @ApiModelProperty("商品名")
    private String goodsName;

    @NotNull(message = "商品价格不能为空")
    @ApiModelProperty("商品价格")
    private BigDecimal goodsPrice;

    @NotNull(message = "商品数量不能为空")
    @ApiModelProperty("商品数量")
    private Integer goodsNum;

    @NotNull(message = "商品新旧程度（几成新）不能为空")
    @ApiModelProperty("商品新旧程度")
    private String goodsLevel;

    @NotNull(message = "商品到期时间不能为空")
    @ApiModelProperty("商品到期时间")
    private String goodsDue;

    @NotNull(message = "商品详情不能为空")
    @ApiModelProperty("商品详情")
    private String goodsIntro;

}
