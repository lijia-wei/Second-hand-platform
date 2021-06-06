package com.jess.secondarymarket.forms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @program: secondarymarket
 * @description: 商品更新表单
 * @author: Jess
 * @create: 2021-05-27 22:46
 **/
@Data
public class UpdateGoodForm {

    @NotNull(message = "商品id不能为空")
    @ApiModelProperty("商品id")
    private Long id;

    @NotNull(message = "商品详情不能为空")
    @ApiModelProperty("商品详情")
    private String goodsIntro;

    @NotNull(message = "商品价格不能为空")
    @ApiModelProperty("商品价格")
    private BigDecimal goodsPrice;
}
