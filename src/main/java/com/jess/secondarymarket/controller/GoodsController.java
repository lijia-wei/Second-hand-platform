package com.jess.secondarymarket.controller;

import com.jess.secondarymarket.accessctro.RoleContro;
import com.jess.secondarymarket.enums.RoleEnum;
import com.jess.secondarymarket.forms.CreateGoodForm;
import com.jess.secondarymarket.forms.UpdateGoodForm;
import com.jess.secondarymarket.service.GoodsService;
import com.jess.secondarymarket.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @program: secondarymarket
 * @description: 商品接口
 * @author: Jess
 * @create: 2021-05-27 22:34
 **/

@RestController
@RequestMapping("/api/goods")
@Slf4j
@Api(tags = "商品相关")
@CrossOrigin
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("新增商品")
    @PostMapping(name = "新增商品", value = "/insertGoods")
    @RoleContro(role = RoleEnum.USER)
    public ResultVO insertGoods(@Valid CreateGoodForm creatGoodForm){
        return goodsService.insertGoods(creatGoodForm);
    }

    @ApiOperation("修改商品")
    @PostMapping(name = "修改商品", value = "/updateGoods")
    @RoleContro(role = RoleEnum.USER)
    public ResultVO updateGoods(@Valid UpdateGoodForm updateGoodForm){
        return goodsService.updateGoods(updateGoodForm);
    }

    @ApiOperation("查看商品详情")
    @PostMapping(name = "查看商品详情", value = "/goodsDetail")
    public ResultVO goodsDetail(@RequestParam Long goodsId){
        return goodsService.goodsDetail(goodsId);
    }

    @ApiOperation("删除指定商品")
    @PostMapping(name = "删除指定商品", value = "/deleteGoods")
    public ResultVO deleteGoods(Long goodsId){
        return goodsService.deleteGoods(goodsId);
    }

    @ApiOperation("查看指定标签下的所有商品")
    @PostMapping(name = "查看指定标签下的所有商品", value = "/getAllGoodsByTag")
    public ResultVO getAllGoodsByTag(@RequestParam int tagId){
        return goodsService.getAllGoodsByTag(tagId);
    }
}
