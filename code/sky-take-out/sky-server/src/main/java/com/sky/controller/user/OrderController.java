package com.sky.controller.user;

import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.Result;
import com.sky.vo.OrderSubmitVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.service.OrderSerivce;

/**
 * 用户订单相关数据的操作
 */
@RestController("userOrderController")
@RequestMapping("/user/order")
@Api(tags = "用户段订单的线管接口")
public class OrderController {

    @Autowired
    private OrderSerivce orderSerivce;

    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO){
        OrderSubmitVO orderSubmitVO = orderSerivce.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }
}
