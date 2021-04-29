package vip.ph.vueapp.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import vip.ph.vueapp.model.EmailLogin;
import vip.ph.vueapp.mapper.EmailLoginMapper;
import vip.ph.vueapp.service.EmailLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.ph.vueapp.service.MailService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Just be alive
 * @since 2021-04-29
 */
@Service
public class EmailLoginServiceImpl extends ServiceImpl<EmailLoginMapper, EmailLogin> implements EmailLoginService {

    @Resource
    private EmailLoginMapper emailLoginMapper;

    @Resource
    private MailService mailService;
    @Override
    public void createAccount(EmailLogin emailLogin) {
        String token = IdUtil.createSnowflake(5, 3).nextIdStr();
        String salt = RandomUtil.randomString(8);
        String md5Pwd = SecureUtil.md5(emailLogin.getPassword() + salt);
        LocalDateTime ldt = LocalDateTime.now().plusDays(1);
        emailLogin.setSalt(salt);
        emailLogin.setActivetionTime(ldt);
        emailLogin.setPassword(md5Pwd);
        emailLogin.setIsActive("0");
        emailLogin.setToken(token);
        emailLoginMapper.insertEmailLogin(emailLogin);

        //TODD 发送邮件功能
        String activationUrl = "http://localhost/email-login/activationUrl?token=" + emailLogin.getToken();
        mailService.sendMailForActivationAccount(activationUrl,emailLogin.getEmail());
    }

    @Override
    public void loginAccount(EmailLogin emailLogin) {
        /**
         * 根据邮箱查询用户
         * 查询不到结果，说明账户不存在和账户为激活
         * 如果查询到多个账户，提示：请联系管理员账号异常。
         */
        List<EmailLogin> emailList = emailLoginMapper.selectEmail(emailLogin.getEmail());
        if (emailList == null || emailList.isEmpty()){
            //TODD 抛出自定义异常
        }

        if (emailList.size() > 1){
            // TODD 抛出自定义异常
        }

        EmailLogin login = emailList.get(0);

        String md5Pass = SecureUtil.md5(emailLogin.getPassword() + emailLogin.getSalt());

        if (!login.getPassword().equals(md5Pass)){
            // TODD 抛出自定义异常
        }

    }

    @Override
    public void activationUrl(String token) {
        EmailLogin emailLogin = emailLoginMapper.selectEmailLoginByToken(token);

        boolean after = LocalDateTime.now().isAfter(emailLogin.getActivetionTime());
        if (after){
            // 自定义异常管理
        }
        emailLoginMapper.update(token);
    }
}
