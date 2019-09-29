package ua.company.spring.SpringUniversityAdmissionSystem.presentation.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Specialty;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.University;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.service.EducationService;
import ua.company.spring.SpringUniversityAdmissionSystem.util.AttributeNames;
import ua.company.spring.SpringUniversityAdmissionSystem.util.Path;

import java.util.Objects;
import java.util.Optional;

@Controller
@SessionAttributes("user")
public class UniversitySelectionController {
    private static final int RECORDS_PER_PAGE = 9;
    private EducationService service;

    public UniversitySelectionController(EducationService service) {
        this.service = service;
    }

    @GetMapping("/universitySelection")
    public String getUniversitySelectionPage(@ModelAttribute User user,
                                             @RequestParam(value = "page", required = false) Integer page,
                                             ModelMap model) {
        Optional<Specialty> chosenSpecialty = service.getChosenSpecialty(user);
        if (chosenSpecialty.isPresent()) {
            model.addAttribute(AttributeNames.CHOSEN_SPECIALTY, chosenSpecialty.get());
        } else {
            if (Objects.isNull(page)) page = 0;
            Page<University> universities = service.getUniversities(PageRequest.of(page, RECORDS_PER_PAGE));
            model.addAttribute(AttributeNames.PAGE, page + 1);
            model.addAttribute(AttributeNames.NUMBER_OF_PAGES, universities.getTotalPages());
            model.addAttribute(AttributeNames.UNIVERSITIES, universities.getContent());
        }
        return Path.UNIVERSITY_SELECTION_PAGE;
    }

    @GetMapping("/changeSpecialty")
    public String changeSpecialty(@ModelAttribute User user) {
        service.dropUserSpecialtyRequest(user);
        return "forward:/universitySelection";
    }
}
