package com.phl.blog.util;


import com.phl.blog.entity.User;
import org.springframework.util.DigestUtils;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-01-17:18
 */
public class MD5util{


    public static void main(String[] args) {
        User user = new User();
        user.setPassword("123456");
        String md5Pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        System.out.println(md5Pwd);
    }
}
