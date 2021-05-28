package com.jess.secondarymarket.forms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: secondarymarket
 * @description: 添加评论表单
 * @author: Jess
 * @create: 2021-05-28 15:37
 **/
@Data
public class InsertCommentForm {

    @ApiModelProperty("被评论商品id")
    @NotNull(message = "被评论商品id")
    private Long goodsId;

    @ApiModelProperty("评论内容")
    @NotNull(message = "评论内容不能为空")
    private String comMsg;
}
