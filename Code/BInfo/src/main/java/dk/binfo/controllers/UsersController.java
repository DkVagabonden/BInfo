package dk.binfo.controllers;

import dk.binfo.models.User;
import dk.binfo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users/add")
    public ModelAndView adminCreateNewUser(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("users", new User());
        modelAndView.setViewName("/users/add");
        return modelAndView;
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public ModelAndView adminCreateUser(@Valid User users, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        User userExists = userService.findUserByEmail(users.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "Der eksisterer allerede en bruger med den angivne email");
        }
        else {
            userService.adminSaveUser(users);
            modelAndView.addObject("successMessage", "SUCCES!: Du har tilføjet en ny bruger.");
            modelAndView.addObject("users", new User());
            modelAndView.setViewName("/users/add");
        }
        return modelAndView;
    }

    @RequestMapping("/users")
    public ModelAndView showUsers() {
        ModelAndView modelAndView = new ModelAndView("/users", "users", userService.findAll());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/users");
        return modelAndView;
    }

}
