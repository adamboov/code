package com.adam.code.service.impl;

import com.adam.code.entity.User;
import com.adam.code.repository.UserRepository;
import com.adam.code.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户service实现类
 * @author VAIO-adam
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
