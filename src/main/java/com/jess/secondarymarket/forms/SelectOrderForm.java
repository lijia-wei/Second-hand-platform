package com.jess.secondarymarket.forms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: secondarymarket
 * @description: TODO
 * @author: Jess
 * @create: 2021-06-01 10:19
 **/
@Data
public class SelectOrderForm {

    @NotNull(message = "不能为空")
    @ApiModelProperty("id")
    private Integer orderId;

    private Integer sellerId;

    private Long goodId;

    private String createTime;
}
