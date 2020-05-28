package com.phl.blog.service;

import com.phl.blog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-01-18:13
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    List<Type> typeList();

    List<Type> typeListToTop(Integer size);

    Type getTypeByName(String typeName);

    Page<Type> Types(Pageable pageable);

    Type updateType(Long id,Type type);

    void deleteType(Long id);
}
