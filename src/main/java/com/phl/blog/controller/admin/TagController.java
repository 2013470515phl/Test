package com.phl.blog.controller.admin;

import com.phl.blog.entity.Tag;
import com.phl.blog.service.TagService;
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

import java.security.PublicKey;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-03-15:10
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String getTags(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                          Model model){
        model.addAttribute("page",tagService.getTags(pageable));
        return "/admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "/admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editTag(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagService.getTagById(id));
        return "/admin/tags-input";
    }

    @PostMapping("/tags")
    public String saveTag(Tag tag, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getTagName());
        if(tag1 !=null){
            attributes.addFlashAttribute("message","操作失败！(名称重复)");
        }else{
            Tag t = tagService.saveTag(tag);
            if(t == null){
                attributes.addFlashAttribute("message","操作失败！");
            }else{
                attributes.addFlashAttribute("message","操作成功！");
            }
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String updateType(@PathVariable Long id,Tag tag, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getTagName());
        if(tag1 != null){
            attributes.addFlashAttribute("message","操作失败！(名称重复)");
        }else{
            Tag t = tagService.updateTag(id,tag);
            if(t == null){
                attributes.addFlashAttribute("message","操作失败！");
            }else{
                attributes.addFlashAttribute("message","操作成功！");
            }
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","操作成功！");
        return "redirect:/admin/tags";
    }



}
