package com.phl.blog.service;

import com.phl.blog.NotFoundException;
import com.phl.blog.dao.TagsRepository;
import com.phl.blog.entity.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-03-15:12
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public Tag getTagById(Long id) {
        return tagsRepository.findById(id).get();
    }

    @Override
    public Page<Tag> getTags(Pageable pageable) {
        return tagsRepository.findAll(pageable);
    }

    @Override
    public Tag getTagByName(String tagName) {
        return tagsRepository.findByTagName(tagName);
    }

    @Override
    public List<Tag> tagList(String ids) {
        return tagsRepository.findAllById(convertToList(ids));
    }

    @Override
    public List<Tag> tagListTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return tagsRepository.findTop(pageable);
    }

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids) && ids != null){
            String[] idsarr = ids.split(",");
            for(int i=0;i < idsarr.length;i++){
                list.add(new Long(idsarr[i]));
            }
        }
        return list;
    }

    @Override
    public List<Tag> tagList() {
        return tagsRepository.findAll();
    }

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagsRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag tag1 = tagsRepository.findById(id).get();
        if(tag1 == null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(tag,tag1);
        return tagsRepository.save(tag1);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagsRepository.deleteById(id);
    }
}
