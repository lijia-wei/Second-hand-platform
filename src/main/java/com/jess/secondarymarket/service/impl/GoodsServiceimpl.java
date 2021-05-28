package com.jess.secondarymarket.service.impl;

import com.jess.secondarymarket.dao.CommentMapper;
import com.jess.secondarymarket.dao.GoodsMapper;
import com.jess.secondarymarket.dao.PublishMapper;
import com.jess.secondarymarket.enums.ResultEnum;
import com.jess.secondarymarket.forms.CreateGoodForm;
import com.jess.secondarymarket.forms.UpdateGoodForm;
import com.jess.secondarymarket.model.Goods;
import com.jess.secondarymarket.model.Publish;
import com.jess.secondarymarket.model.User;
import com.jess.secondarymarket.service.GoodsService;
import com.jess.secondarymarket.service.UserService;
import com.jess.secondarymarket.util.ResultVOUtil;
import com.jess.secondarymarket.vo.GoodsInfoVO;
import com.jess.secondarymarket.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: secondarymarket
 * @description: 商品相关操作
 * @author: Jess
 * @create: 2021-05-27 15:30
 **/
@Service
public class GoodsServiceimpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PublishMapper publishMapper;
    @Override

    public ResultVO goodsDetail(Long goodsId) {
        GoodsInfoVO goodsInfoVO = goodsMapper.selectByGoodsId(goodsId);
        if (goodsInfoVO == null) {
            return ResultVOUtil.error(ResultEnum.ARTICLE_NOT_EXIST);
        }

        //阅读量+1
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        if(goods == null){
            return ResultVOUtil.error(ResultEnum.ARTICLE_NOT_EXIST);
        }else{
            goods.setGoodsRead(goods.getGoodsRead() + 1);
            goodsMapper.updateByPrimaryKey(goods);
        }

        return ResultVOUtil.success(goods);
    }

    @Override
    public ResultVO insertGoods(CreateGoodForm creatGoodForm) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(creatGoodForm, goods);
        goods.setGoodsLike((long)0);
        goods.setGoodsRead((long)0);
        goods.setGoodsStatus(1);
        goods.setGoodsComment(null);
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        goods.setCreateTime(dateFormat.format(date));
        if (goodsMapper.insert(goods) != 1) {
            return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
        }
        return ResultVOUtil.success(goods);
    }


    @Override
    public ResultVO updateGoods(UpdateGoodForm updateGoodForm) {
        Goods goods = goodsMapper.selectByPrimaryKey(updateGoodForm.getId());
        if(goods == null){
            return ResultVOUtil.error(ResultEnum.ARTICLE_NOT_EXIST);
        }
        BeanUtils.copyProperties(updateGoodForm, goods);
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        goods.setUpdateTime(dateFormat.format(date));
        if (goodsMapper.updateByPrimaryKey(goods) == 1) {
            return ResultVOUtil.success(goods);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

}
