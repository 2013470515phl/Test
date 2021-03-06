package com.phl.blog.controller.admin;

import com.phl.blog.entity.Type;
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


/**
 * @author Penghanlin-verysix
 * @create 2020-04-01-18:29
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page",typeService.Types(pageable));
        return "/admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "/admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editType(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "/admin/types-input";
    }

    @PostMapping("/types")
    public String saveType(Type type, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getTypeName());
        if(type1 != null){
            attributes.addFlashAttribute("message","操作失败！(名称重复)");
        }else{
            Type t = typeService.saveType(type);
            if(t == null){
                attributes.addFlashAttribute("message","操作失败！");
            }else{
                attributes.addFlashAttribute("message","操作成功！");
            }
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String updateType(@PathVariable Long id,Type type, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getTypeName());
        if(type1 != null){
            attributes.addFlashAttribute("message","操作失败！(名称重复)");
        }else{
            Type t = typeService.updateType(id,type);
            if(t == null){
                attributes.addFlashAttribute("message","操作失败！");
            }else{
                attributes.addFlashAttribute("message","操作成功！");
            }
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delType(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","操作成功！");
        return "redirect:/admin/types";
    }
}
