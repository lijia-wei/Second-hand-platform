package com.jess.secondarymarket.service;

import com.jess.secondarymarket.forms.InsertCommentForm;
import com.jess.secondarymarket.vo.CommentVO;
import com.jess.secondarymarket.vo.ResultVO;

import java.util.List;

public interface CommentService {
    /**
     * 新增评论
     */
    ResultVO insertComment(InsertCommentForm form);

    /**
     * 删除评论
     */
    ResultVO deleteComment(int id);

    /**
     * 通过文章id获取所有评论
     */
    List<CommentVO> getAllCommentByBlogId(Long blogId);
}
