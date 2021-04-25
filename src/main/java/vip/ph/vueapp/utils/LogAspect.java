package vip.ph.vueapp.utils;

import com.alibaba.fastjson.JSON;
import com.power.common.util.IpUtil;
import org.apache.tomcat.util.net.IPv6Utils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import vip.ph.vueapp.model.SystemExce;
import vip.ph.vueapp.model.SystemLog;
import vip.ph.vueapp.service.SystemExceService;
import vip.ph.vueapp.service.SystemLogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author Just be alive
 * @version 1.0
 * @date 2021-04-24 - 22:31
 */
@Aspect
@Component
public class LogAspect {


    @Value("${version}")
    private String version;


    @Resource
    private SystemLogService systemLogService;

    @Resource
    private SystemExceService systemExceService;


    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(vip.ph.vueapp.utils.Log)")
    public void logPointcut() {
    }

    /**
     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     */
    @Pointcut("execution(* vip.ph.vueapp.controller..*.*(..))")
    public void exceptionPointcut() {
    }


    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint 切入点
     * @param keys      返回结果
     */
    @AfterReturning(value = "logPointcut()", returning = "keys")
    public void saveSystemLog(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        assert requestAttributes != null;
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        SystemLog systemLog = new SystemLog();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            Log opLog = method.getAnnotation(Log.class);
            if (opLog != null) {
                String operModul = opLog.logModule();
                String operType = opLog.logType();
                String operDesc = opLog.logDesc();

                systemLog.setLogModel(operModul); // 操作模块
                systemLog.setLogType(operType); // 操作类型
                systemLog.setLogDesc(operDesc); // 操作描述
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;

            systemLog.setLogMethod(methodName); // 请求方法

            // 请求的参数
            Map<String, String> rtnMap = convertMap(request.getParameterMap());
            // 将参数所在的数组转换成json



            String params = JSON.toJSONString(rtnMap);

            systemLog.setLogRequestParam(params); // 请求参数
            systemLog.setLogResponseParam(JSON.toJSONString(keys)); // 返回结果
            systemLog.setLogUrl(request.getRequestURI()); // 请求URI
            systemLog.setGmtCreate(new Date()); // 创建时间
            systemLog.setLogIp(IpUtil.getIpAddr(request));
            systemLog.setLogVersion(version); // 操作版本
            systemLogService.save(systemLog);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "exceptionPointcut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        assert requestAttributes != null;
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        SystemExce systemExce = new SystemExce();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            // 请求的参数
            Map<String, String> rtnMap = convertMap(request.getParameterMap());
            // 将参数所在的数组转换成json
            String params = JSON.toJSONString(rtnMap);
            systemExce.setExcepRequestParam(params); // 请求参数
            systemExce.setLogMethod(methodName); // 请求方法名
            systemExce.setExcepName(e.getClass().getName()); // 异常名称
            systemExce.setExcepMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace())); // 异常信息
            systemExce.setLogUrl(request.getRequestURI()); // 操作URI
            systemExce.setLogIp(IpUtil.getIpAddr(request)); // 操作员IP
            systemExce.setLogVersion(version); // 操作版本号
            systemExce.setGmtCreate(new Date()); // 发生异常时间
            systemExceService.save(systemExce);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     */
    public static String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }

    /*** 转换request 请求参数** @param paramMap request获取的参数数组*/
    public static Map<String, String> convertMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }
}
