package com.jess.secondarymarket.service;

import com.jess.secondarymarket.forms.CreateOrderForm;
import com.jess.secondarymarket.vo.ResultVO;

public interface OrderServise {

    /**
     * 购买商品生成订单
     */
    ResultVO createOrder(CreateOrderForm createOrderForm);

    /**
     * 购买交易完成，取消订单
     */
    ResultVO deleteOrder(int orderId);

    /**
     * 用户查看自己申请的订单
     */
    ResultVO selectOrder();
}
