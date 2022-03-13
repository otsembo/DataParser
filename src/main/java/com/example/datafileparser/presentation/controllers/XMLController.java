package com.example.datafileparser.presentation.controllers;

import com.example.datafileparser.common.Constants;
import com.example.datafileparser.data.repository.DataRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class XMLController {

    private final DataRepository dataRepository = new DataRepository();

    @GetMapping("/data/xml")
    public String csv(Model model) throws IOException {
        model.addAttribute("users", Constants.DEFAULT_USERS);
        return "xml";
    }

    @GetMapping("/data/xml/load")
    public String csvLoad(Model model) throws Exception {
        model.addAttribute("users", dataRepository.fetchXML());
        return "xml";
    }

}
