package com.phl.blog.controller.admin;

import com.phl.blog.entity.Blog;
import com.phl.blog.entity.BlogQuery;
import com.phl.blog.entity.User;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * @author Penghanlin-verysix
 * @create 2020-04-01-17:36
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String Blogs(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog, Model model){
        model.addAttribute("types",typeService.typeList());
        model.addAttribute("page",blogService.getBlogs(pageable,blog));
        return "/admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog, Model model){
        model.addAttribute("page",blogService.getBlogs(pageable,blog));
        return "/admin/blogs :: bloglist";
    }


    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("types",typeService.typeList());
        model.addAttribute("tags",tagService.tagList());
        return "/admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/input")
    public String editinput(@PathVariable Long id,Model model){
        model.addAttribute("types",typeService.typeList());
        model.addAttribute("tags",tagService.tagList());
        Blog blog = blogService.getBlogById(id);
        blog.init();
        model.addAttribute("blog",blog);
        return "/admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String save_edit(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.tagList(blog.getTagIds()));
        Blog b;
        if(blog.getId()==null){
            b = blogService.saveBlog(blog);
        }else {
            b = blogService.updateBlog(blog.getId(),blog);
        }

        if(b == null){
            attributes.addFlashAttribute("message","操作失败！");
        }else{
            attributes.addFlashAttribute("message","操作成功！");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","操作成功！");
        return "redirect:/admin/blogs";
    }

}
