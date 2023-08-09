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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import dev.achonma.crimedbms.models.User;
import dev.achonma.crimedbms.services.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "users/addNew")
    public RedirectView addNew(User user, RedirectAttributes redir) {
        userService.save(user);
        RedirectView redirectView = new RedirectView("/login", true);
        redir.addFlashAttribute("message",
                "You successfully registered! You can now login");
        return redirectView;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> userList = userService.get();
        User user = userService.getCurrentUser();
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        model.addAttribute("users", userList);
        return "users";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute("users") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String editStateForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElse(null);
        model.addAttribute("users", user);
        return "edituser";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("users") User user, Model model) {
        User existingUser = userService.findById(id).orElse(null);
        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setUsername(user.getUsername());

        userService.save(existingUser);
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/delete/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
