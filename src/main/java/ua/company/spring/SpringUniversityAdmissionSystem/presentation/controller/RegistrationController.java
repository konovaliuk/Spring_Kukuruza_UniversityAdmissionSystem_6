package ua.company.spring.SpringUniversityAdmissionSystem.presentation.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.service.RegistrationService;
import ua.company.spring.SpringUniversityAdmissionSystem.service.exception.RegistrationException;
import ua.company.spring.SpringUniversityAdmissionSystem.util.AttributeNames;
import ua.company.spring.SpringUniversityAdmissionSystem.util.Path;

@Controller
@SessionAttributes(types = User.class)
@Log4j2
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String getRegistration() {
        log.info("Try to get registration page");
        return Path.REGISTRATION_PAGE;
    }

    @PostMapping("/registration")
    public String postRegistration(@RequestParam("login") String login,
                                   @RequestParam("first_name") String firstName,
                                   @RequestParam("second_name") String secondName,
                                   @RequestParam("passport_code") String passportCode,
                                   @RequestParam("gender") String gender,
                                   @RequestParam("email") String email,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("password") String password,
                                   Model model) {
        log.info("Start of new user registration");
        User newUser = User.builder()
                .login(login)
                .firstName(firstName)
                .secondName(secondName)
                .passportCode(passportCode)
                .gender(gender)
                .email(email)
                .phone(phone)
                .password(password)
                .build();
        User saved = registrationService.register(newUser);
        model.addAttribute(AttributeNames.USER, saved);
        log.info("User was successfully register");
        return Path.INDEX_PAGE;
    }

    @ExceptionHandler(RegistrationException.class)
    public ModelAndView registrationError(RegistrationException e) {
        log.info("User registration fail", e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(AttributeNames.REGISTRATION_ERROR, e.getMessage());
        modelAndView.setViewName("registration");
        return modelAndView;
    }
}
