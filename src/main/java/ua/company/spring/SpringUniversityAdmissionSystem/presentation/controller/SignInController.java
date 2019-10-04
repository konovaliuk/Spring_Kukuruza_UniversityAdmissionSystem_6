package ua.company.spring.SpringUniversityAdmissionSystem.presentation.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.service.SignInService;
import ua.company.spring.SpringUniversityAdmissionSystem.service.exception.SignInException;
import ua.company.spring.SpringUniversityAdmissionSystem.util.AttributeNames;
import ua.company.spring.SpringUniversityAdmissionSystem.util.Path;
import ua.company.spring.SpringUniversityAdmissionSystem.util.UserTypes;

@Controller
@SessionAttributes(types = User.class)
@AllArgsConstructor
public class SignInController {
    private final SignInService signInService;

    @GetMapping("/signIn")
    public String getSignIn() {
        return Path.SIGN_IN_PAGE;
    }

    @PostMapping("/signIn")
    public String postSignIn(@RequestParam("login") String login,
                             @RequestParam("password") String password,
                             Model model) {
        User user = signInService.signIn(login, password);
        model.addAttribute(AttributeNames.USER, user);
        int userRoleId = user.getUserType().getId();
        if (userRoleId == UserTypes.STUDENT.getId()) {
            return Path.INDEX_PAGE;
        }
        if (userRoleId == UserTypes.ADMIN.getId()) {
            return Path.ADMIN_PAGE;
        }
        throw new RuntimeException("Wrong UserType");
    }

    @ExceptionHandler(SignInException.class)
    public ModelAndView signInError(SignInException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(AttributeNames.SIGN_IN_ERROR, e.getMessage());
        modelAndView.setViewName("signIn");
        return modelAndView;
    }
}
