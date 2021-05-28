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
     * 收藏商品
     */


}
