package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
@Api(tags = "C端购物车相关接口")
public class ShoppingCartController {

    private static final Logger log = LoggerFactory.getLogger(ShoppingCartController.class);

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     *
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("购物车添加函数")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        //如果是json的操作比如增加一个@RequestBody 注解
        log.info("添加购物车{}", shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    /**
     * 查看购物车列表
     * @param userId
     * @return
     */
    @GetMapping("/view")
    @ApiOperation("查看购物车")
    public Result<List<ShoppingCart>> view(Integer userId) {
        log.info("购物车查看物品{}", userId);
        List<ShoppingCart> list = shoppingCartService.listView(userId);
        return Result.success(list);
    }
}
