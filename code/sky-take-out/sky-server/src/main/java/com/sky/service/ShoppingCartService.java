package com.sky.service;

import com.sky.dto.ShoppingCartDTO;

/**
 * 购物车的结构
 */
public interface ShoppingCartService {



    /**
     * 添加购物车
     * @param shoppingCartDTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
