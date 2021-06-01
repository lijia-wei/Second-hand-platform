package com.jess.secondarymarket.forms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: secondarymarket
 * @description: TODO
 * @author: Jess
 * @create: 2021-06-01 09:08
 **/

@Data
public class CreateOrderForm {

    @ApiModelProperty("商品id")
    @NotNull(message = "商品id")
    private Long goodId;
}
