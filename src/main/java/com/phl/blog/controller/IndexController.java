package com.phl.blog.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;

/**
 * @author Penghanlin-verysix
 * @create 2020-03-30-16:46
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String GetIndex(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                           Model model){
        model.addAttribute("page",blogService.blogList(pageable));
        model.addAttribute("types",typeService.typeListToTop(6));
        model.addAttribute("tags",tagService.tagListTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommendTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model){
        model.addAttribute("page",blogService.blogList("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String getBlog(@PathVariable Long id, Model model){
        model.addAttribute("blog",blogService.getBlogByIdToHtml(id));
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.listRecommendTop(3));
        return "_fagment :: newBlogList";
    }
}
