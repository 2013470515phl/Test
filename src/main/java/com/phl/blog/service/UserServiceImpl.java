package com.phl.blog.service;

import com.phl.blog.dao.UserRepository;
import com.phl.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-01-16:12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName, DigestUtils.md5DigestAsHex(password.getBytes()));
    }
}
