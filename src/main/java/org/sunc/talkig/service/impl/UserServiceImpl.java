package org.sunc.talkig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.sunc.talkig.entity.User;
import org.sunc.talkig.mapper.UserMapper;
import org.sunc.talkig.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author talk
 * @since 2023-05-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    // @Override
    // public Long getIdByUserName(String username) {
    //     return userMapper.getIdByUserName(username);
    // }
}
