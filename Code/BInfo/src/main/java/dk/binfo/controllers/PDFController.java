package dk.binfo.controllers;

import dk.binfo.models.User;
import dk.binfo.services.ListService;
import dk.binfo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PDFController {

    @Autowired
    private UserService userService;

    @Autowired
    private ListService listService;

    @RequestMapping(value={"/lists/pdf"})
    public ModelAndView generateInternListPDF() {
        System.out.println("PDF TEST 1");
        ModelAndView modelAndView = new ModelAndView("/lists/pdf", "list", listService
                .generateList(Integer.MAX_VALUE, 2));
        System.out.println("PDF TEST 2");
        listService.generatePDF(Integer.MAX_VALUE, 33);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        System.out.println("PDF TEST 3");
        modelAndView.addObject(user);
        return modelAndView;
    }
}
