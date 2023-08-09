package dev.achonma.crimedbms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.achonma.crimedbms.models.Crime;
import dev.achonma.crimedbms.models.User;
import dev.achonma.crimedbms.services.CrimeService;
import dev.achonma.crimedbms.services.UserService;

@Controller
@RequestMapping("")
public class CrimeController {
    @Autowired
    private CrimeService crimeService;

    @Autowired
    private UserService userService;

    @GetMapping("/crimes")
    public String getCrimes(Model model) {
        List<Crime> crimeList = crimeService.get();
        User user = userService.getCurrentUser();
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        model.addAttribute("crimes", crimeList);
        return "crimes";
    }

    @GetMapping("/createnewcrime")
    public String createCrimeForm(Model model) {
        Crime crime = new Crime();
        User user = userService.getCurrentUser();
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        model.addAttribute("crimes", crime);
        return "createnewcrime";
    }

    @PostMapping("/crimes")
    public String saveCrime(@ModelAttribute("crimes") Crime crime) {
        crimeService.save(crime);
        return "redirect:/crimes";
    }

    @GetMapping("/crimes/update/{id}")
    public String editCrimeForm(@PathVariable Long id, Model model) {
        model.addAttribute("crimes", crimeService.get(id));
        return "editcrime";
    }

    @PostMapping("/crimes/update/{id}")
    public String updateCrime(@PathVariable Long id, @ModelAttribute("crime") Crime crime, Model model) {
        Crime existingCrime = crimeService.get(id);
        existingCrime.setName(crime.getName());
        crimeService.save(existingCrime);
        return "redirect:/crimes";
    }

    @RequestMapping(value = "/crimes/delete/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
    public String deleteCrime(@PathVariable Long id) {
        crimeService.delete(id);
        return "redirect:/crimes";
    }
}
