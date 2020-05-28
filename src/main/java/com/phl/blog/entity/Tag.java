package com.phl.blog.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Penghanlin-verysix
 * @create 2020-03-31-16:18
 */
@Entity
@Table(name = "t_tag")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();


    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                '}';
    }

    public Tag(Long id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public Tag() {
    }
}
