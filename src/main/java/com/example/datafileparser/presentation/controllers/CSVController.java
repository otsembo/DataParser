package com.example.datafileparser.presentation.controllers;


import com.example.datafileparser.common.Constants;
import com.example.datafileparser.data.repository.DataRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class CSVController {

    private final DataRepository dataRepository = new DataRepository();

    @GetMapping("/data/csv")
    public String csv(Model model) throws IOException {
        model.addAttribute("users", Constants.DEFAULT_USERS);
        return "csv";
    }

    @GetMapping("/data/csv/load")
    public String csvLoad(Model model) throws IOException {
        model.addAttribute("users", dataRepository.fetchCSV());
        return "csv";
    }

}
