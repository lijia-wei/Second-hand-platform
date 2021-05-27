package com.jess.secondarymarket.dao;

import com.jess.secondarymarket.model.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    Goods selectByPrimaryKey(Long id);

    List<Goods> selectAll();

    int updateByPrimaryKey(Goods record);
}