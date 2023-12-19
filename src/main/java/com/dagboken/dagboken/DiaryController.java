package com.dagboken.dagboken;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
   @GetMapping("/showBetweenDates")
    public String showBetweenDates() {
        return "showBetweenDates";
    }

    @PostMapping ("/showBetweenDates")
    public String showBetweenDates(@RequestParam  Date startDate, 
                                @RequestParam Date endDate, Model model) {
        List<Diary> showBetweenDates = diaryRepository.findDiaryBetweenDates(startDate, endDate);
        model.addAttribute("pages", showBetweenDates);
        return "showBetweenDates";
    }

    @GetMapping("/addDiaryPage")
    public String addDiaryPage(Model model) {
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
       return "redirect:/showAll";
    }

    @PostMapping("/changeDiary/{id}")
    public String changeTitle(@PathVariable int id, @ModelAttribute Diary newDiary) {
        Diary findMe = diaryRepository.findById(id).orElse(null);
        if(findMe != null) {
            findMe.setTitle(newDiary.getTitle());
            findMe.setText(newDiary.getText());
            findMe.setDate(newDiary.getDate());

            diaryRepository.save(findMe);
        }
        return "redirect:/showAll";
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
