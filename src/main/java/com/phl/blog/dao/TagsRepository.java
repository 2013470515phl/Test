package com.phl.blog.dao;

import com.phl.blog.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-03-15:10
 */

public interface TagsRepository extends JpaRepository<Tag,Long> {

    Tag findByTagName(String tagName);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);

}
