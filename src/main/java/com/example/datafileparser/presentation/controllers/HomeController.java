package com.example.datafileparser.presentation.controllers;

import com.example.datafileparser.common.Constants;
import com.example.datafileparser.data.repository.DataRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) throws IOException {
        model.addAttribute("name", "data");
        model.addAttribute("download_url", Constants.FILE_DOWNLOAD_URL);
        System.out.println(new DataRepository().data());
        return "index";
    }

}
