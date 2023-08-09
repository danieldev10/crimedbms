package dev.achonma.crimedbms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.achonma.crimedbms.models.CriminalRecord;
import dev.achonma.crimedbms.models.User;
import dev.achonma.crimedbms.services.CriminalRecordService;
import dev.achonma.crimedbms.services.UserService;

@Controller
public class ApplicationController {
    @Autowired
    private CriminalRecordService criminalRecordService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        List<CriminalRecord> criminalRecordList = criminalRecordService.get();
        model.addAttribute("criminalrecords", criminalRecordList);
        User user = userService.getCurrentUser();
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        return "index";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/index")
    public String homePageLink(Model model) {
        List<CriminalRecord> criminalRecordList = criminalRecordService.get();
        model.addAttribute("criminalrecords", criminalRecordList);
        User user = userService.getCurrentUser();
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        return "index";
    }
}
