package com.jess.secondarymarket.service;

import com.jess.secondarymarket.forms.CreateGoodForm;
import com.jess.secondarymarket.forms.UpdateGoodForm;
import com.jess.secondarymarket.vo.ResultVO;

public interface GoodsService {
    /**
     * 商品详情
     */
    ResultVO goodsDetail(Long goodsId);


    /**
     * 发布商品
     */
    ResultVO insertGoods(CreateGoodForm creatGoodForm);


    /**
     * 发布商品
     */
    ResultVO updateGoods(UpdateGoodForm updateGoodForm);

    /**
     * 删除指定商品
     */
    ResultVO deleteGoods(Long goodsId);


    /**
     * 查看指定标签下的所有商品
     */
    ResultVO getAllGoodsByTag(int tagId);

}
