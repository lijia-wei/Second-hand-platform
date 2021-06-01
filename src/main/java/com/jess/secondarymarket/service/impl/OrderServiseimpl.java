package com.jess.secondarymarket.service.impl;

import com.jess.secondarymarket.dao.GoodsMapper;
import com.jess.secondarymarket.dao.OrderMapper;
import com.jess.secondarymarket.dao.UserMapper;
import com.jess.secondarymarket.enums.ResultEnum;
import com.jess.secondarymarket.forms.CreateOrderForm;
import com.jess.secondarymarket.forms.SelectOrderForm;
import com.jess.secondarymarket.forms.UserOrderForm;
import com.jess.secondarymarket.model.Goods;
import com.jess.secondarymarket.model.Order;
import com.jess.secondarymarket.model.User;
import com.jess.secondarymarket.service.OrderServise;
import com.jess.secondarymarket.service.UserService;
import com.jess.secondarymarket.util.ResultVOUtil;
import com.jess.secondarymarket.vo.GoodsInfoVO;
import com.jess.secondarymarket.vo.ResultVO;
import com.jess.secondarymarket.vo.SelectOrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: secondarymarket
 * @description: 订单
 * @author: Jess
 * @create: 2021-05-28 16:02
 **/
@Service
public class OrderServiseimpl implements OrderServise {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultVO createOrder(CreateOrderForm createOrderForm) {
        User user = userService.getCurrentUser();
        if(user == null ){
            return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN);
        }
        Order order = new Order();
        Goods goods = goodsMapper.selectByPrimaryKey(createOrderForm.getGoodId());
        if(goods.getGoodsStatus() == 0){
            return ResultVOUtil.error(ResultEnum.GOODS_IS_ORDER);
        }

        goods.setGoodsStatus(0);//更改状态为正在交易中
        goodsMapper.updateByPrimaryKey(goods);
        order.setSellerId(goods.getUserId());
        order.setOrderId(user.getId().intValue());
        BeanUtils.copyProperties(createOrderForm, order);
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        order.setCreateTime(dateFormat.format(date));
        int insert = orderMapper.insert(order);
        if(insert == 0){
            return ResultVOUtil.error(ResultEnum.CREATE_ORDER_ERROR);
        }else {
            return ResultVOUtil.success();
        }
    }

    /**
     * orderId 订单id
     */
    @Override
    public ResultVO deleteOrder(int orderId) {
        User user = userService.getCurrentUser();
        if(user == null ){
            return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN);
        }
        Order order = orderMapper.selectByPrimaryKey(orderId);
        goodsMapper.deleteByPrimaryKey(order.getGoodId());
        if(order == null){
            return ResultVOUtil.error(ResultEnum.ORDER_NOT_EXIST);
        }else{
            orderMapper.deleteByPrimaryKey(orderId);
            return ResultVOUtil.success();
        }
    }

    @Override
    public ResultVO selectOrder() {
        User user = userService.getCurrentUser();
        if(user == null ){
            return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN);
        }
        List<UserOrderForm> orderList = orderMapper.selectByUserId(user.getId());
        if(orderList == null)
        {
            return ResultVOUtil.error(ResultEnum.ORDER_NOT_EXIST);
        }

        List<SelectOrderVO> selectOrderVOList = new ArrayList<>();

        for (UserOrderForm order:orderList) {
            User user1 = userMapper.selectByPrimaryKey((long)order.getSellerId());//卖家
            GoodsInfoVO goods = goodsMapper.selectByGoodsId(order.getGoodId());
            SelectOrderVO selectOrderVO = new SelectOrderVO();
            selectOrderVO.setUserName(user1.getUserName());
            selectOrderVO.setUserCredit(user1.getUserCredit());
            selectOrderVO.setGoodsLevel(goods.getGoodsLevel());
            selectOrderVO.setGoodsDue(goods.getGoodsDue());
            selectOrderVO.setGoodsPrice(goods.getGoodsPrice());
            selectOrderVO.setGoodsName(goods.getGoodsName());
            selectOrderVOList.add(selectOrderVO);
        }

        return ResultVOUtil.success(selectOrderVOList);
    }
}
