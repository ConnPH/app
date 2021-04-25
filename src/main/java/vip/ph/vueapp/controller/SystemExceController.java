package vip.ph.vueapp.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import vip.ph.vueapp.utils.Log;
import vip.ph.vueapp.utils.LogMethod;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Just be alive
 * @since 2021-04-24
 */
@RestController
@RequestMapping("/system-exce")
@Api(tags = "系统异常管理AOP")
public class SystemExceController {


    @GetMapping("/hello")
    @Log(logModule = "测试模块",logType = LogMethod.SELECT,logDesc = "test")
    public String hello(String hello){

        try {
            int i  = 10/0;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "1111111" + hello;
    }
}

