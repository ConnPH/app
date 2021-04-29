package vip.ph.vueapp.service;

/**
 * @author Just be alive
 * @version 1.0
 * @date 2021-04-29 - 15:59
 */
public interface MailService {


    void sendMailForActivationAccount(String activationUrl,String email);
}
