package com.jess.secondarymarket.dao;

import com.jess.secondarymarket.model.Publish;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Publish record);

    Publish selectByPrimaryKey(Integer id);

    List<Publish> selectAll();

    int updateByPrimaryKey(Publish record);

    List<Publish> selectByUserId();
}