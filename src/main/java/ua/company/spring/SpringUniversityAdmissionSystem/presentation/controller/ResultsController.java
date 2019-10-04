package ua.company.spring.SpringUniversityAdmissionSystem.presentation.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Grade;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.service.ResultsService;
import ua.company.spring.SpringUniversityAdmissionSystem.util.AttributeNames;
import ua.company.spring.SpringUniversityAdmissionSystem.util.Path;

import java.util.List;

@Controller
@SessionAttributes("user")
@AllArgsConstructor
@Log4j2
public class ResultsController {
    private final ResultsService service;

    @GetMapping("/results")
    public String results(@ModelAttribute User user, ModelMap model) {
        log.info("Try to get user results page");
        List<Grade> grades = service.getUserGrades(user);
        model.addAttribute(AttributeNames.USER_GRADES, grades);
        return Path.RESULTS_PAGE;
    }
}
