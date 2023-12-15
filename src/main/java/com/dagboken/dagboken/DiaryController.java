package com.dagboken.dagboken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiaryController {

    @Autowired
    private DiaryRepository diaryRepository;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("pages", diaryRepository.findMeToday());
        return "index";
    }
}
