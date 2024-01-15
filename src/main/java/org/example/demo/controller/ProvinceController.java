package org.example.demo.controller;

import org.example.demo.model.Province;
import org.example.demo.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    IProvinceService provinceService;

    @GetMapping("")
    public ModelAndView showHomePage(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("provinces", provinceService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/create");
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView create(@Valid Province province, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/create");
            modelAndView.addObject("listErrors", bindingResult.getAllErrors());
            return modelAndView;
        }
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("redirect:/provinces");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("province", provinceService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@Valid Province province, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("ListErrorsEdit", bindingResult.getAllErrors());
            return modelAndView;
        }
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("redirect:/provinces");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        provinceService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/provinces");
        return modelAndView;
    }

    @GetMapping("/search/{name}")
    public ModelAndView search(@RequestParam String searchText){
        ModelAndView modelAndView = new ModelAndView("/result");
        modelAndView.addObject("provincesSearch",provinceService.findByName(searchText));
        return modelAndView;
    }

    @GetMapping("/showDetails/{id}")
    public ModelAndView showDetails(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/details");
        modelAndView.addObject("province", provinceService.findById(id).get());
        return modelAndView;
    }
}
