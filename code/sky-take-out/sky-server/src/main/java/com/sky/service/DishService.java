package com.sky.service;

import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DishService {
    /**
     * 分页查询菜品操作
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除
     * @param ids
     */

    void deleteBash(List<Long> ids);

    /**
     * 设置状态
     * @param vo
     */
    void setDishStatue(DishVO vo);
}
