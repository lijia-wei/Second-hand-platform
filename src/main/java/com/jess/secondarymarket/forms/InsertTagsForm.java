package com.jess.secondarymarket.forms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: secondarymarket
 * @description: 添加标签表单
 * @author: Jess
 * @create: 2021-05-28 09:00
 **/

@Data
public class InsertTagsForm {

    @NotNull(message = "标签名称不能为空")
    @ApiModelProperty("标签名称")
    private String tagsName;

    @ApiModelProperty("标签详情")
    private String tagsMsg;

}
