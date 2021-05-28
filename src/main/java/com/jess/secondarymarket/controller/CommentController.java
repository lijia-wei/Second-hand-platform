package com.jess.secondarymarket.controller;

import com.jess.secondarymarket.forms.InsertCommentForm;
import com.jess.secondarymarket.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: secondarymarket
 * @description: 商品评论接口
 * @author: Jess
 * @create: 2021-05-28 15:42
 **/
@RestController
@RequestMapping("/api/comment")
@Slf4j
@Api(tags = "商品评论相关")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation("评论文章")
    @PostMapping(name = "评论某篇文章",value = "/insert")
    public Object creatComment(InsertCommentForm form) {
        return commentService.insertComment(form);
    }

    @ApiOperation("删除评论")
    @PostMapping(name = "删除评论",value = "/delete")
    public Object creatComment(Long id) {
        return commentService.deleteComment(id);
    }
}
