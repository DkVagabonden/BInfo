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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@Controller
public class PDFController {

    @Autowired
    private UserService userService;

    @Autowired
    private ListService listService;

    @RequestMapping(value={"/lists/pdf"})
    public ModelAndView generateInternListPDF() {
        System.out.println("\n* Initiating listService.generateList *\n");
        ModelAndView modelAndView = new ModelAndView("/lists/internal", "list", listService
                .generateList(Integer.MAX_VALUE, 2));
        System.out.println("\n* Generating PDF *\n");
        String filePath = "/Users/jensbackvall/Desktop/PDF_TEST/binfoPDF.pdf"; // filePath
        listService.generatePDF(Integer.MAX_VALUE, 33, filePath);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        System.out.println("\n* Generating onscreen messages *\n");
        modelAndView.addObject("adminMessage","Du er logget ind som spadmin");
        modelAndView.addObject("PDFMessage","PDF er gemt på /Users/jensbackvall/Desktop/PDF_TEST/binfoPDF.pdf!");
        modelAndView.addObject(user);
        return modelAndView;
    }
}
