package vip.ph.vueapp.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import vip.ph.vueapp.model.EmailLogin;
import vip.ph.vueapp.service.EmailLoginService;
import vip.ph.vueapp.utils.Log;
import vip.ph.vueapp.utils.LogMethod;
import vip.ph.vueapp.utils.Result;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Just be alive
 * @since 2021-04-29
 */
@RestController
@RequestMapping("/email-login")
@Api(tags = "邮箱注册登录")
public class EmailLoginController {

    @Resource
    private EmailLoginService emailLoginService;


    @PostMapping("createAccount")
    @Log(logModule = "邮箱注册",logType = LogMethod.POST,logDesc = "邮箱注册")
    public Result createAccount(EmailLogin emailLogin){
        emailLoginService.createAccount(emailLogin);
        return Result.ok("注册成功");
    }


    @PostMapping("loginAccount")
    @Log(logModule = "邮箱登录",logType = LogMethod.POST,logDesc = "邮箱登录")
    public Result loginAccount(EmailLogin emailLogin){
        emailLoginService.loginAccount(emailLogin);
        return Result.ok("登录成功");
    }


    @GetMapping("activationUrl")
    @Log(logModule = "邮箱激活",logType = LogMethod.GET,logDesc = "邮箱激活")
    public Result getActivationUrl(String token){
        emailLoginService.activationUrl(token);
        return Result.ok();
    }
}

