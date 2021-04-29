package vip.ph.vueapp.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import vip.ph.vueapp.service.MailService;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author Just be alive
 * @version 1.0
 * @date 2021-04-29 - 15:59
 */
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String mailName;

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private TemplateEngine templateEngine;


    /**
     * 激活邮箱账号
     * @param activationUrl
     * @param email
     */
    @Override
    public void sendMailForActivationAccount(String activationUrl,String email){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
            message.setSubject("个人账号激活");
            message.setFrom(mailName);
            message.setTo(email);
            message.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("activationUrl",activationUrl);
            String engine = templateEngine.process("activationUrl.html", context);
            message.setText(engine);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
