package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.controller.admin.CategoryController;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.InterfaceExtension;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 切面类
 */

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    private static Logger log = LoggerFactory.getLogger(AutoFillAspect.class);

    /**
     * 切入点
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut() {
    }

    /**
     * 前置通知
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行公共字段的自动填充");
        //获得反射的类
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获得方法的注解
        AutoFill annotation = methodSignature.getMethod().getAnnotation(AutoFill.class);
        //获得注解的类型
        OperationType value = annotation.value();
        //获得方法的参数
        Object[] args = joinPoint.getArgs();
        if (args.length == 0 || args == null) {
            return;
        }
        //找到实体的对象
        Object arg = args[0];
        //准备对应的数据
        LocalDateTime now = LocalDateTime.now();
        Long currid = BaseContext.getCurrentId();//获取id
        //判断是那几种情况需要进行操作
        if (value == OperationType.INSERT) {
            //4中情况都要赋值
            try {
                Method createTimes = arg.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
                Method setUpdateTime = arg.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setCreateUser = arg.getClass().getDeclaredMethod("setCreateUser", Long.class);
                Method setUpdateUser = arg.getClass().getDeclaredMethod("setUpdateUser", Long.class);
                //反射获得的数据
                createTimes.invoke(arg,now);
                setUpdateTime.invoke(arg,now);
                setCreateUser.invoke(arg,currid);
                setUpdateUser.invoke(arg,currid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (value == OperationType.UPDATE) {
            //只有更新两种就可以
            try {
                Method setUpdateTime = arg.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateUser = arg.getClass().getDeclaredMethod("setUpdateUser", Long.class);
                //反射获得的数据
                setUpdateTime.invoke(arg,now);
                setUpdateUser.invoke(arg,currid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
