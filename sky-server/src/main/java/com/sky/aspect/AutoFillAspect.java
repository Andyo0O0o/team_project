package com.sky.aspect;


import com.sky.annotation.AutoFill;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.weaver.JoinPointSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义切面类，实现公共字段自动填充处理逻辑
 */

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 指定切入点
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")

    public void autoFillPointCut(){}

    /**
     * 前置通知，为公共字段赋值
     */
    @Before("autoFillPointCut()")
    public void autoFIll(JoinPoint joinPoint){
        log.info("开始进行公共字段自动填充");
        //获取到当前被拦截方法上的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value();
        //获取到当前被拦截方法的参数--实体对象

        //准备赋值的数据

        //根据当前不同的操作类型，为对应的属性通过反射来赋值


    }


}
