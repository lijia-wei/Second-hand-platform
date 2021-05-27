package com.jess.secondarymarket.dao;

import com.jess.secondarymarket.model.Tags;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tags record);

    Tags selectByPrimaryKey(Integer id);

    List<Tags> selectAll();

    int updateByPrimaryKey(Tags record);
}