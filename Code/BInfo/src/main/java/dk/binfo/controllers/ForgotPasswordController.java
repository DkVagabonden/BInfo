package dk.binfo.controllers;

import dk.binfo.models.User;
import dk.binfo.services.EmailService;
import dk.binfo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value={"/forgotpassword"}, method = RequestMethod.GET)
    public ModelAndView glemtpassword(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forgotpassword");
        return modelAndView;
    }

    @RequestMapping(value={"/forgotpasswordlink"}, method = RequestMethod.GET)
    public ModelAndView glemtpasswordlink(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forgotpasswordlink");
        return modelAndView;
    }

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public ModelAndView createNewApartment(@RequestParam String email) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserByEmail(email);
        if (user == null) {
            modelAndView.addObject("Message", "Emailen existere ikke, prøv igen.");
        }else{
            modelAndView.addObject("Message", "SUCCES!: Der er sent et link til din email!");
            sendResetEmail(user);
        }
        return modelAndView;
    }

    public void sendResetEmail(User user){
        String body = "<B>Reset dit password</B><br><br>";
        body += "Hvis du har bedt om at få dit password resat,<br> beder vi dig ";
        body += "beder vi dig klikke på nedenstående link.<br><br>";
        body += "<a href=\"http://localhost:8080/forgotpassword?user="+user.getPassword()+"\">Klik her for at reset dit password</a><br><br>";
        body += "Har du ikke bedt om et reset skal du bare ignore denne email.<br><br>";
        body += "Mvh<br>";
        body += "Jens Boligforening<br>";
        body += "nørrebrogade 123<br>";
        body += "2312 nørrebro";

        emailService.generateAndSendEmail("jensbackvall@t-nova.org","BoligInfo password reset",body);
    }




}
