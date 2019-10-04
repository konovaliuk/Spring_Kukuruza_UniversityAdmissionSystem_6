package ua.company.spring.SpringUniversityAdmissionSystem.presentation.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.EducationOption;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Request;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.service.SummaryRatingService;
import ua.company.spring.SpringUniversityAdmissionSystem.util.AttributeNames;
import ua.company.spring.SpringUniversityAdmissionSystem.util.Path;

import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("user")
@AllArgsConstructor
@Log4j2
public class SummaryRatingController {
    private final SummaryRatingService service;

    @GetMapping("/rating")
    public String getSummaryRating(@ModelAttribute User user, ModelMap model) {
        log.info("Try to get summary rating page");
        Optional<Request> userRequest = service.getUserRequest(user);
        if (userRequest.isPresent()) {
            EducationOption educationOption = userRequest.get().getEducationOption();
            List<Request> requests = service.getRequestsByEducationOptionOrderByRating(educationOption);
            model.addAttribute(AttributeNames.REQUESTS, requests);
        }
        return Path.SUMMARY_RATING_PAGE;
    }
}
