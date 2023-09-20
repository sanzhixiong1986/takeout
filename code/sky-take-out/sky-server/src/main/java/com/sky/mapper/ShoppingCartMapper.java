package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 查询数据
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * 根据id操作更新number数据
     */
    @Update("update shopping_cart set number=#{number} where id=#{id}")
    void updateNumberById(ShoppingCart shoppingCart);

    /**
     * 插入数据
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart (name,user_id,dish_id,setmeal_id,dish_flavor,number,amount,image,create_time")"+"value (#{name})"
    void insert(ShoppingCart shoppingCart);


    @Select("select * from shopping_cart userId = #{userId}")
    List<ShoppingCart> viewList(Integer userId);

    /**
     * 清空购物车
     * @param userId
     */
    void deleteByUserId(Long userId);
}
