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
@TableName("t_system_exce")
@ApiModel(value="SystemExce对象", description="SystemExce对象")
public class SystemExce extends Model<SystemExce> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "异常ID")
      @TableId(value = "excep_id", type = IdType.AUTO)
    private Integer excepId;

    @ApiModelProperty(value = "异常请求参数")
    private String excepRequestParam;

    @ApiModelProperty(value = "异常方法名")
    private String excepName;

    @ApiModelProperty(value = "异常信息")
    private String excepMessage;

    @ApiModelProperty(value = "操作人ID")
    private Integer logUserId;

    @ApiModelProperty(value = "操作人姓名")
    private String logUserName;

    @ApiModelProperty(value = "操作方法")
    private String logMethod;

    @ApiModelProperty(value = "操作URL")
    private String logUrl;

    @ApiModelProperty(value = "操作IP")
    private String logIp;

    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;

    private String logVersion;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.excepId;
    }

}
