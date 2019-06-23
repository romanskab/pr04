package com.oktenweb.pr04.controllers;

import com.oktenweb.pr04.entity.MyFile;
import com.oktenweb.pr04.entity.Type;
import com.oktenweb.pr04.dao.MyFileDAO;
import com.oktenweb.pr04.utils.LocalDateTimeCustomEditor;
import lombok.AllArgsConstructor;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {

    private MyFileDAO myFileDAO;

    private LocalDateTimeCustomEditor localDateTimeCustomEditor;

    @InitBinder("myFile")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, localDateTimeCustomEditor);
        System.out.println("init!!!!!!!!!");
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("myFile", MyFile.builder()
                .type(Type.HOME)
                .timeFinish(org.joda.time.LocalDateTime.now())
                .build());
        model.addAttribute("types", Type.values());
        return "home";
    }

    @PostMapping("/save")
    public String save(MyFile myFile, @RequestParam MultipartFile image){
        String path = System.getProperty("user.home")
                + File.separator
                + "images"
                + File.separator
                + image.getOriginalFilename();
        try {
            image.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        myFile.setPlace(path);
        myFile.setSize(image.getSize());
        myFile.setName(image.getOriginalFilename());
        myFile.setTimeStart(org.joda.time.LocalDateTime.now());
        System.out.println(myFile);

        myFileDAO.save(myFile);
        return "redirect:/";
    }

    @GetMapping("/findAll")
    public String findAll(Model model){
        List<MyFile> all = myFileDAO.findAll();
        model.addAttribute("all", all);
        return "findAll";
    }
}
