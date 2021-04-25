package vip.ph.vueapp.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import vip.ph.vueapp.model.SystemLog;
import vip.ph.vueapp.service.SystemLogService;
import vip.ph.vueapp.utils.Log;
import vip.ph.vueapp.utils.LogMethod;
import vip.ph.vueapp.utils.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Just be alive
 * @since 2021-04-24
 */
@RestController
@RequestMapping("/system-log")
@Api(tags = "系统日志管理AOP")
public class SystemLogController {

    @Autowired
    private SystemLogService systemLogService;


    @PostMapping("findSystemLogByPage/{currentPage}/{pageSize}")
    @Log(logModule = "系统日志管理AOP",logType = LogMethod.SELECT,logDesc = "分页查询日志信息")
    public Result findSystemLogByPage(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){

        Page<SystemLog> page = new Page<SystemLog>(currentPage,pageSize);
        Page<SystemLog> systemLogIPage = systemLogService.page(page);
        return Result.ok(systemLogIPage);
    }

}

