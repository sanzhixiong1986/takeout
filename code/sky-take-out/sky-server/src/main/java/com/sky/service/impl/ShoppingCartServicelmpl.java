package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import com.sky.vo.DishItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServicelmpl implements ShoppingCartService {

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Autowired
    DishMapper dishMapper;

    @Autowired
    SetmealMapper setmealMapper;

    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        //当前商品是否存在，已经存在只需要将数量加1，如果不存在就插入对应的数据
        ShoppingCart shoppingCart = new ShoppingCart();
        //属性拷贝
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);
        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);
        //判断是否存在
        if (list != null && list.size() > 0) {
            ShoppingCart cart = (ShoppingCart) list.get(0);     //只能查出一条数据出来
            cart.setNumber(cart.getNumber() + 1);
            //需要更新
            shoppingCartMapper.updateNumberById(cart);
        } else {
            //需要一个购物车的对象

            //判断是否是菜品还是套餐
            Long dishId = shoppingCartDTO.getDishId();//菜品
            Long setmealId = shoppingCartDTO.getSetmealId();
            if(dishId != null){
                //本次是添加是菜品
                Dish dish = dishMapper.getId(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());

            }else{
//                List<DishItemVO> dishItemBySetmealId = setmealMapper.getDishItemBySetmealId(setmealId);

            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            //插入数据
            shoppingCartMapper.insert(shoppingCart);
        }
    }
}
