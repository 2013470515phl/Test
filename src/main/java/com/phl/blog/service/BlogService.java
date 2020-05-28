package com.phl.blog.service;

import com.phl.blog.entity.Blog;
import com.phl.blog.entity.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-03-16:30
 */
public interface BlogService {

    Blog getBlogById(Long id);

    Blog getBlogByIdToHtml(Long id);

    Page<Blog> getBlogs(Pageable pageable, BlogQuery blog);

    Page<Blog> blogList(Pageable pageable);

    Page<Blog> blogList(Long tagId,Pageable pageable);

    Page<Blog> blogList(String query,Pageable pageable);

    Map<String,List<Blog>> archiveBlog();

    List<Blog> listRecommendTop(Integer size);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

    Long countBlog();
}
