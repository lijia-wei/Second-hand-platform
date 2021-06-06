package com.jess.secondarymarket.service.impl;

import com.jess.secondarymarket.dao.*;
import com.jess.secondarymarket.enums.ResultEnum;
import com.jess.secondarymarket.forms.CreateGoodForm;
import com.jess.secondarymarket.forms.UpdateGoodForm;
import com.jess.secondarymarket.model.Goods;
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
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PublishMapper publishMapper;

    @Autowired
    private TagsMapper tagsMapper;

    @Override
    public ResultVO goodsDetail(Long goodsId) {
        GoodsInfoVO goodsInfoVO = goodsMapper.selectByGoodsId(goodsId);
        if (goodsInfoVO == null) {
            return ResultVOUtil.error(ResultEnum.GOODS_NOT_EXIST);
        }

        //阅读量+1
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        if(goods == null){
            return ResultVOUtil.error(ResultEnum.GOODS_NOT_EXIST);
        }else{
            goods.setGoodsRead(goods.getGoodsRead() + 1);
            goodsMapper.updateByPrimaryKey(goods);
        }

        return ResultVOUtil.success(goods);
    }

    @Override
    public ResultVO insertGoods(CreateGoodForm creatGoodForm) {
        User user = userService.getCurrentUser();
        if(user == null ){
            return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN);
        }
        Goods goods = new Goods();
        BeanUtils.copyProperties(creatGoodForm, goods);
        goods.setUserId(user.getId().intValue());
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
        User user = userService.getCurrentUser();
        if(user == null ){
            return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN);
        }
        Goods goods = goodsMapper.selectByPrimaryKey(updateGoodForm.getId());
        if(goods == null){
            return ResultVOUtil.error(ResultEnum.GOODS_NOT_EXIST);
        }
        BeanUtils.copyProperties(updateGoodForm, goods);
        goods.setUserId(user.getId().intValue());
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        goods.setUpdateTime(dateFormat.format(date));
        if (goodsMapper.updateByPrimaryKey(goods) == 1) {
            return ResultVOUtil.success(goods);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO deleteGoods(Long goodsId) {
        User user = userService.getCurrentUser();
        if(user == null ){
            return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN);
        }
        if(goodsMapper.selectByGoodsId(goodsId) == null){
            return ResultVOUtil.error(ResultEnum.GOODS_NOT_EXIST);
        }
        int result = goodsMapper.deleteByPrimaryKey(goodsId);
        if(result == 0){
            return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
        }
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO getAllGoodsByTag(int tagId) {
        if(tagsMapper.selectByPrimaryKey(tagId) == null){
            return ResultVOUtil.error(ResultEnum.TAG_NOT_EXIST);
        }
        List<Goods> goodsList = goodsMapper.selectByTagsId(tagId);
        return ResultVOUtil.success(goodsList);
    }
}
