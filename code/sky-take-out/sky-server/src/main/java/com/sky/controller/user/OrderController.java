package com.sky.controller.user;

import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.Result;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sky.service.OrderSerivce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户订单相关数据的操作
 */
@RestController("userOrderController")
@RequestMapping("/user/order")
@Api(tags = "用户段订单的线管接口")
public class OrderController {

    @Autowired
    private OrderSerivce orderSerivce;

    private static final Logger log = LoggerFactory.getLogger(ShopController.class);

    /**
     * 用户下单
     *
     * @param ordersSubmitDTO
     * @return
     */
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        OrderSubmitVO orderSubmitVO = orderSerivce.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }

    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    @PutMapping("/payment")
    @ApiOperation("订单支付")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("订单支付：{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderSerivce.payment(ordersPaymentDTO);
        log.info("生成预支付交易单：{}", orderPaymentVO);
        return Result.success(orderPaymentVO);
    }

    /**
     * 客户催单
     * @param id
     * @return
     */
    @GetMapping("/reminder/{id}")
    @ApiOperation("客户催单")
    public Result reminder(@PathVariable("id") Long id) {
        log.info("用户催单，订单号", id);
        orderSerivce.reminder(id);
        return Result.success();
    }
}
