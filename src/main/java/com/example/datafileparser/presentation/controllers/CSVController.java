package com.example.datafileparser.presentation.controllers;


import com.example.datafileparser.common.Constants;
import com.example.datafileparser.data.model.User;
import com.example.datafileparser.data.repository.DataRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class CSVController {

    private final DataRepository dataRepository = new DataRepository();
    private final User emptyUser = new User();
    private boolean isUpdate = false;

    @GetMapping("/data/csv")
    public String csv(Model model) throws IOException {
        model.addAttribute("updated_user", emptyUser);
        model.addAttribute("users", Constants.DEFAULT_USERS);
        model.addAttribute("update", isUpdate);
        return "csv";
    }

    @GetMapping("/data/csv/load")
    public String csvLoad(Model model) throws IOException {
        model.addAttribute("updated_user", emptyUser);
        model.addAttribute("users", dataRepository.fetchCSV());
        model.addAttribute("update", isUpdate);
        return "csv";
    }

    @PostMapping("/data/csv/load/update")
    public String updateCSV(Model model, User userUpdate) throws IOException{
        model.addAttribute("updated_user", userUpdate);
        model.addAttribute("users", dataRepository.updateCSV(userUpdate));
        model.addAttribute("update", true);
        return "csv";
    }

    @GetMapping("/data/csv/load/delete/{id}")
    public String deleteCSV(Model model, @PathVariable int id) throws IOException{
        model.addAttribute("updated_user", emptyUser);
        model.addAttribute("users", dataRepository.deleteFromCSV(id));
        model.addAttribute("update", true);
        return "csv";
    }

}
