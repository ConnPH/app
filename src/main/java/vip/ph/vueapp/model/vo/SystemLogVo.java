package vip.ph.vueapp.model.vo;

import lombok.Data;

/**
 * @author Just be alive
 * @version 1.0
 * @date 2021-04-25 - 21:54
 */
@Data
public class SystemLogVo {
    /**
     * 操作员
     */
    private String username;

    /**
     * 模块
     */
    private String type;
}
