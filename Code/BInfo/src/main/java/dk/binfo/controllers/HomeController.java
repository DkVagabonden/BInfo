package dk.binfo.controllers;

import dk.binfo.models.List_and_seniority;
import dk.binfo.models.User;
import dk.binfo.repositories.HomeRepository;
import dk.binfo.services.SeniorityService;
import dk.binfo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private SeniorityService seniorityService;

    @Autowired
    private HomeRepository homeRepository;

    @RequestMapping(value= "/home", method = RequestMethod.GET)
    public ModelAndView userHome(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());



        List<List_and_seniority> seniority2 = seniorityService.findAll();


        int found = 0;
        int size = seniority2.size();
        if (size >= found) {

         /*   for (int x = 0; x <= size-1; x++)
            {
                List_and_seniority seniority = seniority2.get(x);
                System.out.println("Email " + seniority.getEmail() + " List_priority " + seniority.getList_priority() + " Seniority " + seniority.getSeniority());
                found++;
            } */
         ;

            for (List_and_seniority seniority:seniorityService.findAll()) {
                System.out.println("Email " + seniority.getEmail() + " List_priority " + seniority.getList_priority() + " Seniority " + seniority.getSeniority());
            }
        }


        modelAndView.addObject("user", user);
        modelAndView.addObject("userMessage","Du er logget ind");
        modelAndView.setViewName("/home");
        return modelAndView;
    }
}
