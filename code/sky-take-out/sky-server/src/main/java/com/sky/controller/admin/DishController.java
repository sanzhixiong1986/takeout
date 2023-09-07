package com.sky.controller.admin;

import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 菜品相关操作
 */
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")

public class DishController {
    @Autowired
    private DishService dishService;

    private static Logger log = LoggerFactory.getLogger(DishController.class);

    /**
     * 菜品分页操作相关
     *
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("删除一个的操作")
    public Result delDish(@RequestParam List<Long> ids) {
        log.info("删除的产品{}", ids);
        dishService.deleteBash(ids);
        return Result.success();
    }

    /**
     * 设置是否棋手状态
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result setTatue(@PathVariable("status") Integer status, Long id){
        log.info("设置状态{},{}",status,id);
        DishVO vo = new DishVO();
        vo.setStatus(status);
        vo.setId(id);
        dishService.setDishStatue(vo);
        return Result.success();
    }
}
