package com.dagboken.dagboken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiaryController {

    @Autowired
    private DiaryRepository diaryRepository;

    @GetMapping ("/")
    public String getIndex(Model model) {
        model.addAttribute("pages", diaryRepository.findMeToday());
        return "index";
    }

    @GetMapping("/addDiaryPage")
    public String createEntryForm(Model model) {
        model.addAttribute("diary", new Diary());
        return "diary";
    }

    @PostMapping ("/addDiaryPage")
    public String addDiaryPage(@ModelAttribute Diary diary) {
        diaryRepository.save(diary);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {

       System.out.println("delete mapping: " + id);
       diaryRepository.deleteById(id);
       return "redirect:/";
    }

    @GetMapping("/showAll")
    public String showAll(Model model) {
        model.addAttribute("pages", diaryRepository.findAll());
        return "showAll";
    }

    @GetMapping("/deleteAll")
    public String deleteAll() {
        diaryRepository.deleteAll();
        return "redirect:/";
    }

}
