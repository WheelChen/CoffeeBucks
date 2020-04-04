package me.wheelchen.coffebucks.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Dao层 性能检测切面
 *
 * @author Kelvin Chen
 * @date 2020-03-20 23:51:20
 */
@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    /**
     * 设置切入点
     * 设置操作行为
     *
     * @param pjp 连接点
     * @return
     * @throws Throwable 异常父类
     */
    @Around("repositoryOps()")
    public Object logPerformance(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String name = "-";
        String result = "Y";
        try {
            name = pjp.getSignature().toShortString();
            return pjp.proceed();
        } catch (Throwable t) {
            result = "N";
            throw t;
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("{};{};{}ms", name, result, endTime - startTime);
        }
    }

    @Pointcut("execution(* me.wheelchen.coffebucks.repository..*(..))")
    private void repositoryOps() {

    }
}
