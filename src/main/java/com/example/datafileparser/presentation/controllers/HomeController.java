package com.example.datafileparser.presentation.controllers;

import com.example.datafileparser.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("name", "data");
        model.addAttribute("download_url", Constants.FILE_DOWNLOAD_URL);
        return "index";
    }

}
