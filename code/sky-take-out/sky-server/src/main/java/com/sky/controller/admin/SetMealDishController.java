package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealDishService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 套餐相干的操作
 */
@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetMealDishController {

    @Autowired
    SetmealDishService setmealDishService;

    private static final Logger log = LoggerFactory.getLogger(SetMealDishController.class);

    @GetMapping("/page")
    @ApiOperation("分页的相关操作")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("套餐的分页{}",setmealPageQueryDTO);
        PageResult pageResult = setmealDishService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

}
