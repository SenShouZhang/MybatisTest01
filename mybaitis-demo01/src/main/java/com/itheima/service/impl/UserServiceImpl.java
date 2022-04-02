package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author zhang
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-04-02 20:20:53
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




