package com.jess.secondarymarket.controller;

import com.jess.secondarymarket.accessctro.RoleContro;
import com.jess.secondarymarket.enums.RoleEnum;
import com.jess.secondarymarket.forms.CreateOrderForm;
import com.jess.secondarymarket.service.OrderServise;
import com.jess.secondarymarket.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: secondarymarket
 * @description: 订单接口
 * @author: Jess
 * @create: 2021-06-01 09:20
 **/
@RestController
@RequestMapping("/api/order")
@Api(tags = "订单")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderServise orderServise;

    @ApiOperation("创建订单")
    @PostMapping(name = "创建订单", value = "/createOrder")
    @RoleContro(role = RoleEnum.USER)
    public ResultVO createOrder(CreateOrderForm createOrderForm){
        return orderServise.createOrder(createOrderForm);
    }


    @ApiOperation("删除订单")
    @PostMapping(name = "删除订单", value = "/deleteOrder")
    @RoleContro(role = RoleEnum.USER)
    public ResultVO deleteOrder(int orderId){
        return orderServise.deleteOrder(orderId);
    }
}
