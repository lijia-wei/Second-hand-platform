package com.jess.secondarymarket.controller;

import com.jess.secondarymarket.accessctro.RoleContro;
import com.jess.secondarymarket.enums.RoleEnum;
import com.jess.secondarymarket.forms.CreateOrderForm;
import com.jess.secondarymarket.service.OrderServise;
import com.jess.secondarymarket.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultVO createOrder(@RequestBody CreateOrderForm createOrderForm){
        return orderServise.createOrder(createOrderForm);
    }


    @ApiOperation("删除订单")
    @PostMapping(name = "删除订单", value = "/deleteOrder")
    @RoleContro(role = RoleEnum.USER)
    public ResultVO deleteOrder(@RequestBody int orderId){
        return orderServise.deleteOrder(orderId);
    }

    @ApiOperation("查看用户全部订单")
    @PostMapping(name = "查看用户全部订单", value = "/selectOrder")
    @RoleContro(role = RoleEnum.USER)
    public ResultVO selectOrder(){
        return orderServise.selectOrder();
    }
}
