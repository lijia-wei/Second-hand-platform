package com.jess.secondarymarket.controller;

import com.jess.secondarymarket.accessctro.RoleContro;
import com.jess.secondarymarket.enums.RoleEnum;
import com.jess.secondarymarket.forms.InsertTagsForm;
import com.jess.secondarymarket.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: secondarymarket
 * @description: 标签接口
 * @author: Jess
 * @create: 2021-05-28 09:07
 **/
@RestController
@RequestMapping("/api/tags")
@Api(tags = "标签")
@CrossOrigin
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation("添加标签")
    @PostMapping(name = "添加标签", value = "/insertTag")
    @RoleContro(role = RoleEnum.ADMIN)
    public Object insertTags(@RequestBody InsertTagsForm form) {
        return tagService.insertTags(form);
    }

    @ApiOperation("删除标签")
    @GetMapping(name = "删除标签",value = "/deleteTags")
    @RoleContro(role = RoleEnum.ADMIN)
    public Object deleteTags( Integer id) {
        return tagService.deleteTags(id);
    }

    @ApiOperation("查看所有标签")
    @GetMapping(name = "查看所有标签",value = "/selectAll")
    @RoleContro(role = RoleEnum.USER)
    public Object selectTags(){
        return tagService.selectTags();
    }
}
