package vip.ph.vueapp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.power.common.util.StringUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import vip.ph.vueapp.model.SystemLog;
import vip.ph.vueapp.model.vo.SystemLogVo;
import vip.ph.vueapp.service.SystemLogService;
import vip.ph.vueapp.utils.Log;
import vip.ph.vueapp.utils.LogMethod;
import vip.ph.vueapp.utils.Result;

import java.util.List;

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
    public Result findSystemLogByPage(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize, SystemLogVo systemLogVo){
        Page<SystemLog> page = new Page<SystemLog>(currentPage,pageSize);
        QueryWrapper<SystemLog> systemLogQueryWrapper = new QueryWrapper<>();
        String username = systemLogVo.getUsername();
        String type = systemLogVo.getType();
        if (!StringUtil.isEmpty(username)){
            systemLogQueryWrapper.like("log_user_name",systemLogVo.getUsername());
        }
        if (!StringUtil.isEmpty(type)){
            systemLogQueryWrapper.eq("log_type",systemLogVo.getType());
            systemLogQueryWrapper.orderByDesc("gmt_create");
        }
        Page<SystemLog> systemLogIPage = systemLogService.page(page);
        return Result.ok(systemLogIPage);
    }


    @DeleteMapping("remove/{id}")
    @Log(logModule = "系统日志管理AOP",logType = LogMethod.DELETE,logDesc = "根据ID删除日志")
    public Result removeLogById(@PathVariable Integer id){
        boolean flag = systemLogService.removeById(id);

        if (flag){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }


    @DeleteMapping("removeLogBatch")
    @Log(logModule = "系统日志管理AOP",logType = LogMethod.DELETE,logDesc = "根据ID批量删除日志")
    public Result removeLogBatch(@RequestBody List<Integer> ids){
        systemLogService.removeByIds(ids);
        return Result.ok();
    }




}

