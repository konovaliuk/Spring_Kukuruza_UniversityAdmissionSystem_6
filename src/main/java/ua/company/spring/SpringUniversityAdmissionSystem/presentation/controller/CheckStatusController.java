package ua.company.spring.SpringUniversityAdmissionSystem.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.service.CheckStatusService;
import ua.company.spring.SpringUniversityAdmissionSystem.util.AttributeNames;
import ua.company.spring.SpringUniversityAdmissionSystem.util.Path;

@Controller
@SessionAttributes("user")
public class CheckStatusController {
    private CheckStatusService service;

    public CheckStatusController(CheckStatusService service) {
        this.service = service;
    }

    @GetMapping("/checkStatus")
    public String checkStatus(@ModelAttribute User user, Model model) {
        User updatedUser = service.getUpdatedUser(user);
        model.addAttribute(AttributeNames.USER, updatedUser);
        return Path.CHECK_STATUS_PAGE;
    }
}
