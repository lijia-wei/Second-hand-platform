package com.jess.secondarymarket.forms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: secondarymarket
 * @description: TODO
 * @author: Jess
 * @create: 2021-06-01 18:07
 **/
@Data
public class UserOrderForm {

    @NotNull(message = "卖家id不能为空")
    @ApiModelProperty("卖家id")
    private Integer sellerId;

    @NotNull(message = "商品id不能为空")
    @ApiModelProperty("商品id")
    private Long goodId;

    @NotNull(message = "创建时间不能为空")
    @ApiModelProperty("创建时间")
    private String createTime;

}
