package com.phl.blog.dao;

import com.phl.blog.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-07-13:35
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {


    List<Comment> findByBlogIdAndPraentCommentIsNull(Long blogId, Sort sort);
}
