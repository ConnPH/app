package vip.ph.vueapp.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_email_login")
@ApiModel(value="EmailLogin对象", description="")
public class EmailLogin extends Model<EmailLogin> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "盐")
    private String salt;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime activetionTime;

    @ApiModelProperty(value = "是否过期")
    private String isActive;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "token")
    private String token;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
