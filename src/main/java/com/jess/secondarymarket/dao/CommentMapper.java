package com.jess.secondarymarket.dao;

import com.jess.secondarymarket.model.Comment;
import com.jess.secondarymarket.model.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    Goods selectGoodsByCommentId(int id);
}