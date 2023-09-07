package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 与菜品关联的相关表
 */

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据id获得相关的
     * @param id
     * @return
     */
    List<Long> getSetmealByDishId(List<Long> id);
}
