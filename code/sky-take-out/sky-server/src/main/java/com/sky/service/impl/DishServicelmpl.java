package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class DishServicelmpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;


    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Transactional
    public void deleteBash(List<Long> ids) {
        //判断是否是起售状态就是dish表中的status状态数据
        for (Long id : ids) {
            Dish dish = dishMapper.getId(id);
            if (dish != null) {
                if (dish.getStatus() == StatusConstant.ENABLE) {
                    throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
                }
            }
        }

        List<Long> setMeals = setmealDishMapper.getSetmealByDishId(ids);
        if (setMeals != null && setMeals.size() > 0) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        //可以删除了
        for (Long id : ids) {
            dishMapper.deleteById(id);
            dishFlavorMapper.deleteByDishId(id);
        }
    }

    public void setDishStatue(DishVO vo) {
        dishMapper.setStatue(vo);
    }

    /**
     * 条件查询菜品和口味
     *
     * @param dish
     * @return
     */
    public List<DishVO> listWithFlavor(Dish dish) {
//        List<Dish> dishList = dishMapper.list(dish);
//
//        List<DishVO> dishVOList = new ArrayList<>();
//
//        for (Dish d : dishList) {
//            DishVO dishVO = new DishVO();
//            BeanUtils.copyProperties(d,dishVO);
//
//            //根据菜品id查询对应的口味
//            List<DishFlavor> flavors = dishFlavorMapper.getByDishId(d.getId());
//
//            dishVO.setFlavors(flavors);
//            dishVOList.add(dishVO);
//        }
//
//        return dishVOList;
        return null;
    }

}
