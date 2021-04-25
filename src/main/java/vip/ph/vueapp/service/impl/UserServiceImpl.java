package vip.ph.vueapp.service.impl;

import vip.ph.vueapp.model.User;
import vip.ph.vueapp.mapper.UserMapper;
import vip.ph.vueapp.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Just be alive
 * @since 2021-04-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
