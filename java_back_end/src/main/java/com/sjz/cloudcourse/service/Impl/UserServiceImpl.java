package com.sjz.cloudcourse.service.impl;

import com.sjz.cloudcourse.entity.User;
import com.sjz.cloudcourse.mapper.UserMapper;
import com.sjz.cloudcourse.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
