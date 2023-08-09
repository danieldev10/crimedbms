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
import dev.achonma.crimedbms.models.Prison;
import dev.achonma.crimedbms.models.State;
import dev.achonma.crimedbms.models.User;
import dev.achonma.crimedbms.services.PrisonService;
import dev.achonma.crimedbms.services.StateService;
import dev.achonma.crimedbms.services.UserService;

@Controller
@RequestMapping("/")
public class PrisonController {
    @Autowired
    private PrisonService prisonService;

    @Autowired
    private StateService stateService;

    @Autowired
    private UserService userService;

    @GetMapping("/prisons")
    public String getPrisons(Model model) {
        List<Prison> prisonList = prisonService.get();
        User user = userService.getCurrentUser();
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        model.addAttribute("prisons", prisonList);
        return "prisons";
    }

    @GetMapping("/createnewprison")
    public String createPrisonForm(Model model) {
        Prison prison = new Prison();
        List<State> stateList = stateService.get();
        User user = userService.getCurrentUser();
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        model.addAttribute("states", stateList);
        model.addAttribute("prisons", prison);
        return "createnewprison";
    }

    @PostMapping("/prisons")
    public String addNewPrison(@ModelAttribute("prisons") Prison prison, Model model) {
        // Retrieve the State object based on the provided state ID
        State state = stateService.get(prison.getState().getId());

        // Set the State object for the Prison
        prison.setState(state);

        prisonService.save(prison);
        return "redirect:/prisons";
    }

    @GetMapping("/prisons/update/{id}")
    public String editPrisonForm(@PathVariable Long id, Model model) {
        List<State> stateList = stateService.get();
        User user = userService.getCurrentUser();
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        model.addAttribute("states", stateList);
        model.addAttribute("prisons", prisonService.get(id));
        return "editprison";
    }

    @PostMapping("/prisons/update/{id}")
    public String updatePrison(@PathVariable Long id, @ModelAttribute("prisons") Prison prison, Model model) {
        Prison existingPrison = prisonService.get(id);
        existingPrison.setName(prison.getName());
        existingPrison.setWarden_name(prison.getWarden_name());
        existingPrison.setAddress(prison.getAddress());
        existingPrison.setEmail(prison.getEmail());
        existingPrison.setPhone(prison.getPhone());

        // Retrieve the State object based on the provided state ID
        State state = stateService.get(prison.getState().getId());

        // Set the State object for the existing Prison
        existingPrison.setState(state);

        prisonService.save(existingPrison);
        return "redirect:/prisons";
    }

    @RequestMapping(value = "/prisons/delete/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
    public String deletePrison(@PathVariable Long id) {
        prisonService.delete(id);
        return "redirect:/prisons";
    }
}