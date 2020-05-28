package com.phl.blog.controller;

import com.phl.blog.entity.BlogQuery;
import com.phl.blog.entity.Tag;
import com.phl.blog.entity.Type;
import com.phl.blog.service.BlogService;
import com.phl.blog.service.TagService;
import com.phl.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-08-14:40
 */
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String types(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model){
        List<Tag> tags = tagService.tagListTop(100000);
        if(id == -1){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.blogList(id,pageable));
        model.addAttribute("activeTag",id);
        return "tags";
    }
}
