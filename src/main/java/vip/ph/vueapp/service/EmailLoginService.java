package vip.ph.vueapp.service;

import vip.ph.vueapp.model.EmailLogin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Just be alive
 * @since 2021-04-29
 */
public interface EmailLoginService extends IService<EmailLogin> {


    void createAccount(EmailLogin emailLogin);

    void loginAccount(EmailLogin emailLogin);


    void activationUrl(String token);
}
