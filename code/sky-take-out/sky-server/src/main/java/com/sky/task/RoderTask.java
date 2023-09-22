package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class RoderTask {
    private static final Logger log = LoggerFactory.getLogger(RoderTask.class);

    @Autowired
    OrderMapper orderMapper;

    @Scheduled(cron = "0 * * * * ? ") //每一分钟
    public void processTimeOut() {
        log.info("定时处理超时订单{}", LocalDateTime.now());
        //查询订单
        List<Orders> list = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, LocalDateTime.now().plusMonths(-15));
        if (list != null && list.size() > 0) {
            for (Orders orders : list) {
                orders.setStatus(Orders.CANCELLED);//更新状态
                orders.setCancelReason("订单超时，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }
    }

    /**
     * 一直处于派送中的
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void orocessDeliveyOrder() {
        log.info("定时处理派送中订单{}", LocalDateTime.now());
        List<Orders> list = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().plusMinutes(-60));
        if (list != null && list.size() > 0) {
            for (Orders orders : list) {
                orders.setStatus(Orders.COMPLETED);//更新状态
                orders.setCancelReason("订单超时，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }
    }
}
