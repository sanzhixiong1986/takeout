package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 与菜品关联的相关表
 */

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据id获得相关的
     * @param dishIds
     * @return
     */
    List<Long> getSetmealByDishId(List<Long> dishIds);

    /**
     * 获得相关的分页的操作
     * @param setmealPageQueryDTO
     */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);
}
