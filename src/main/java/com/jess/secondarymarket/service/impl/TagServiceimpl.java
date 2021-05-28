package com.jess.secondarymarket.service.impl;

import com.jess.secondarymarket.dao.GoodsMapper;
import com.jess.secondarymarket.dao.TagsMapper;
import com.jess.secondarymarket.enums.ResultEnum;
import com.jess.secondarymarket.forms.InsertTagsForm;
import com.jess.secondarymarket.model.Goods;
import com.jess.secondarymarket.model.Tags;
import com.jess.secondarymarket.service.GoodsService;
import com.jess.secondarymarket.service.TagService;
import com.jess.secondarymarket.util.ResultVOUtil;
import com.jess.secondarymarket.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: secondarymarket
 * @description: 标签相关
 * @author: Jess
 * @create: 2021-05-28 07:56
 **/

@Service
public class TagServiceimpl implements TagService {

    @Autowired
    private TagsMapper tagsMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsService goodsService;

    @Override
    public ResultVO insertTags(InsertTagsForm form) {
        if(tagsMapper.selectByTagName(form.getTagsName()) != null){
            return ResultVOUtil.error(ResultEnum.TAG_ALREADY_EAIST);
        }

        Tags Tags = new Tags();
        Tags.setTagsName(form.getTagsName());

        if(tagsMapper.insert(Tags) == 0){
            return ResultVOUtil.error(ResultEnum.TAG_NOT_INSERT);
        }

        return ResultVOUtil.success(Tags);
    }

    @Override
    public ResultVO deleteTags(Integer tagId) {
        if(tagsMapper.selectByPrimaryKey(tagId) == null){
            return ResultVOUtil.error(ResultEnum.TAG_NOT_EXIST);
        }

        //将要删除的标签下的所有文章放入默认标签
        List<Goods> goodsList = goodsMapper.selectByTagsId(tagId);
        for (Goods goods : goodsList) {
            goods.setTagsId(1);
            goodsMapper.updateByPrimaryKey(goods);
        }

        if(tagsMapper.deleteByPrimaryKey(tagId) == 0){
            return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
        }
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO selectTags() {
        List<Tags> tags = tagsMapper.selectAll();
        return ResultVOUtil.success(tags);
    }
}
