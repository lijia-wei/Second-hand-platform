package com.jess.secondarymarket.dao;

import com.jess.secondarymarket.model.Goods;
import com.jess.secondarymarket.vo.GoodsInfoVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    Goods selectByPrimaryKey(Long id);

    List<Goods> selectAll();

    int updateByPrimaryKey(Goods record);

    GoodsInfoVO selectByGoodsId(Long id);


    List<GoodsInfoVO> selectByUserId(Long id);

    List<Goods>  selectByTagsId(int id);

}