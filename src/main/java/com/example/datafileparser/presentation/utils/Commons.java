package com.example.datafileparser.presentation.utils;

import com.example.datafileparser.data.model.User;
import org.springframework.ui.Model;

public class Commons {

    public static void UI_DEFAULTS(Model model){
        model.addAttribute("updated_user", new User());
        model.addAttribute("update", false);
    }

}
