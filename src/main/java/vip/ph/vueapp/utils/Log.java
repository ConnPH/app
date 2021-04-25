package vip.ph.vueapp.utils;

import java.lang.annotation.*;

/**
 * 自定义操作日志注解
 * @author Just be alive
 * @version 1.0
 * @date 2021-04-24 - 22:29
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface Log {
    String logModule() default ""; // 操作模块
    String logType() default "";  // 操作类型
    String logDesc() default "";  // 操作说明
}