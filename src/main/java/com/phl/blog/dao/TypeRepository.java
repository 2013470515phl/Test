package com.phl.blog.dao;

import com.phl.blog.entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-01-18:16
 */
public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByTypeName(String typeName);

    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
