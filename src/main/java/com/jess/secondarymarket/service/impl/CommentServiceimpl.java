package com.jess.secondarymarket.service.impl;

import com.jess.secondarymarket.dao.CommentMapper;
import com.jess.secondarymarket.enums.ResultEnum;
import com.jess.secondarymarket.forms.InsertCommentForm;
import com.jess.secondarymarket.model.Comment;
import com.jess.secondarymarket.model.Goods;
import com.jess.secondarymarket.model.User;
import com.jess.secondarymarket.service.CommentService;
import com.jess.secondarymarket.service.GoodsService;
import com.jess.secondarymarket.service.UserService;
import com.jess.secondarymarket.util.ResultVOUtil;
import com.jess.secondarymarket.vo.CommentVO;
import com.jess.secondarymarket.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: secondarymarket
 * @description: 评论相关操作
 * @author: Jess
 * @create: 2021-05-28 15:21
 **/
@Service
public class CommentServiceimpl implements CommentService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ResultVO insertComment(InsertCommentForm form) {
        User user = userService.getCurrentUser();
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN);
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(form,comment);
        comment.setUserId(user.getId());
        if(commentMapper.insert(comment) == 1) {
            return ResultVOUtil.success(comment);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO deleteComment(int id) {
        User user = userService.getCurrentUser();
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN);
        }

        if(commentMapper.selectByPrimaryKey(id) == null){
            return ResultVOUtil.error(ResultEnum.COMMENT_NOT_EXIST);
        }

        Goods goods = commentMapper.selectGoodsByCommentId(id);
        if(goods == null){
            return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
        }

        if(commentMapper.deleteByPrimaryKey(id) == 0) {
            return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
        }
        return ResultVOUtil.success();
    }

    @Override
    public List<CommentVO> getAllCommentByBlogId(Long blogId) {
        return null;
    }
}
