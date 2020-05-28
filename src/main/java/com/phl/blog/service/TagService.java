package com.phl.blog.service;

import com.phl.blog.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * @author Penghanlin-verysix
 * @create 2020-04-03-15:12
 */
public interface TagService {

    Tag getTagById(Long id);

    Page<Tag> getTags(Pageable pageable);

    Tag getTagByName(String tagName);

    List<Tag> tagList();

    List<Tag> tagListTop(Integer size);

    List<Tag> tagList(String ids);

    Tag saveTag(Tag tag);

    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);


}
