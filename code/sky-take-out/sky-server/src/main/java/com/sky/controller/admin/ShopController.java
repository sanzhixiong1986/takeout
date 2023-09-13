package com.sky.controller.admin;


import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店面相关操作")
public class ShopController {

    private static final Logger log = LoggerFactory.getLogger(ShopController.class);

    public static final String SHOP = "SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置店面的状态
     *
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("设置店面的状态")
    public Result setStatus(@PathVariable Integer status) {
        log.info("设置店面状态{},{}", status,redisTemplate);
        redisTemplate.opsForValue().set(SHOP,status);
        return Result.success();
    }

    /**
     *获取店铺的营业状态
     */
    @GetMapping("/status")
    @ApiOperation("获取店铺的营业状态")
    public Result<Integer> getStatus(){
        Integer shop_status = (Integer)redisTemplate.opsForValue().get(SHOP);
        log.info("设置店面状态{}", shop_status == 1?"营业中":"关门中");
        return Result.success(shop_status);
    }
}
