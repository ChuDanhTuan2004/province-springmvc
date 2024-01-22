package org.example.demo2.controller;

import org.example.demo2.model.Student;
import org.example.demo2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/list")
    public String showList(@PageableDefault(value = 2) Pageable pageable,Model model) {
        model.addAttribute("listStudent", studentRepository.findAll(pageable));
        return "/list";
    }

    @GetMapping("/add")
    public String showCreateForm() {
        return "/create";
    }

    @PostMapping("/add")
    public String add(Student student) {
        studentRepository.save(student);
        return "redirect:/students/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentRepository.findById(id).get());
        return "/edit";
    }

    @PostMapping("/edit/{id}")
    public String save(Student student) {
        studentRepository.save(student);
        return "redirect:/students/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "redirect:/students/list";
    }

}
