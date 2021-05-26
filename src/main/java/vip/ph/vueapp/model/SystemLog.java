package vip.ph.vueapp.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Just be alive
 * @since 2021-04-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_system_log")
@ApiModel(value = "SystemLog对象", description = "SystemLog对象")
public class SystemLog extends Model<SystemLog> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志主键")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    @ApiModelProperty(value = "功能模块")
    private String logModel;

    @ApiModelProperty(value = "操作类型")
    private String logType;

    @ApiModelProperty(value = "操作描述")
    private String logDesc;

    @ApiModelProperty(value = "请求参数")
    private String logRequestParam;

    @ApiModelProperty(value = "返回参数")
    private String logResponseParam;

    @ApiModelProperty(value = "操作人ID")
    private String logUserId;

    @ApiModelProperty(value = "操作人姓名")
    private String logUserName;

    @ApiModelProperty(value = "请求URL")
    private String logUrl;

    @ApiModelProperty(value = "请求方法")
    private String logMethod;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;

    @ApiModelProperty(value = "乐观锁")
    private String logVersion;

    @ApiModelProperty(value = "logIP")
    private String logIp;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer isDelete;


    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

}
