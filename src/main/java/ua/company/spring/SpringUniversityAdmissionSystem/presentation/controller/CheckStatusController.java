package ua.company.spring.SpringUniversityAdmissionSystem.presentation.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@AllArgsConstructor
@Log4j2
public class CheckStatusController {
    private final CheckStatusService service;

    @GetMapping("/checkStatus")
    public String checkStatus(@ModelAttribute User user, Model model) {
        log.info("Start checking status process");
        User updatedUser = service.getUpdatedUser(user);
        model.addAttribute(AttributeNames.USER, updatedUser);
        return Path.CHECK_STATUS_PAGE;
    }
}
