package com.phl.blog.service;

import com.phl.blog.entity.User;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-01-16:11
 */
public interface UserService {

    User login(String userName,String Pwd);
}
