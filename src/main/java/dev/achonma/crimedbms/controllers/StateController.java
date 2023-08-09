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

import dev.achonma.crimedbms.models.State;
import dev.achonma.crimedbms.models.User;
import dev.achonma.crimedbms.services.StateService;
import dev.achonma.crimedbms.services.UserService;

@Controller
@RequestMapping("")
public class StateController {
    @Autowired
    private StateService stateService;

    @Autowired
    private UserService userService;

    @GetMapping("/states")
    public String getStates(Model model) {
        List<State> stateList = stateService.get();
        User user = userService.getCurrentUser();
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        model.addAttribute("states", stateList);
        return "states";
    }

    @GetMapping("/createnewstate")
    public String createStateForm(Model model) {
        State state = new State();
        User user = userService.getCurrentUser();
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        model.addAttribute("states", state);
        return "createnewstate";
    }

    @PostMapping("/states")
    public String savePrison(@ModelAttribute("states") State state) {
        stateService.save(state);
        return "redirect:/states";
    }

    @GetMapping("/states/update/{id}")
    public String editStateForm(@PathVariable Long id, Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        model.addAttribute("states", stateService.get(id));
        return "editstate";
    }

    @PostMapping("/states/update/{id}")
    public String updateState(@PathVariable Long id, @ModelAttribute("states") State state, Model model) {
        State existingState = stateService.get(id);
        existingState.setName(state.getName());
        existingState.setCapital(state.getCapital());
        existingState.setState_code(state.getState_code());

        stateService.save(existingState);
        return "redirect:/states";
    }

    @RequestMapping(value = "/states/delete/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
    public String deleteState(@PathVariable Long id) {
        stateService.delete(id);
        return "redirect:/states";
    }
}
