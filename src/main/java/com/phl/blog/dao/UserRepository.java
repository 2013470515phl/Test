package com.phl.blog.dao;

import com.phl.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-01-16:13
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserNameAndPassword(String userName,String password);
}
