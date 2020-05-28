package com.phl.blog.service;

import com.phl.blog.entity.Comment;

import java.util.List;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-07-13:33
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
