package ua.company.spring.SpringUniversityAdmissionSystem.presentation.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Specialty;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Subject;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.service.EducationService;
import ua.company.spring.SpringUniversityAdmissionSystem.service.exception.SubmitSpecialtyException;
import ua.company.spring.SpringUniversityAdmissionSystem.util.AttributeNames;
import ua.company.spring.SpringUniversityAdmissionSystem.util.Path;

import java.util.List;
import java.util.Objects;

@Controller
@SessionAttributes("user")
public class SpecialtySelectionController {
    private static final int RECORDS_PER_PAGE = 9;
    private EducationService service;

    public SpecialtySelectionController(EducationService service) {
        this.service = service;
    }

    @RequestMapping(value = "/selectUniversity", method = {RequestMethod.POST, RequestMethod.GET})
    public String selectUniversity(@RequestParam("universityId") Integer universityId,
                                   @RequestParam(value = "page", required = false) Integer page,
                                   ModelMap model) {
        if (Objects.isNull(page))
            page = 0;
        Page<Specialty> specialties = service.getSpecialties(universityId, PageRequest.of(page, RECORDS_PER_PAGE));
        model.addAttribute(AttributeNames.PAGE, page + 1);
        model.addAttribute(AttributeNames.NUMBER_OF_PAGES, specialties.getTotalPages());
        model.addAttribute(AttributeNames.SPECIALTIES, specialties.getContent());
        return Path.SPECIALTY_SELECTION_PAGE;
    }

    @PostMapping("/submitSpecialty")
    public String selectSpecialty(@RequestParam("universityId") Integer universityId,
                                  @RequestParam("specialtyId") Integer specialtyId,
                                  @ModelAttribute User user,
                                  ModelMap model) {
        Integer rating = service.getRatingByRequiredSubjects(user, specialtyId);
        Specialty chosenSpecialty = service.submitRequest(user, rating, universityId, specialtyId);
        model.addAttribute(AttributeNames.CHOSEN_SPECIALTY, chosenSpecialty);
        return "redirect:/universitySelection";
    }

    @ExceptionHandler(SubmitSpecialtyException.class)
    public ModelAndView registrationError(WebRequest req) {
        Integer universityId = Integer.valueOf(Objects.requireNonNull(req.getParameter("universityId")));
        Integer specialtyId = Integer.valueOf(Objects.requireNonNull(req.getParameter("specialtyId")));
        int page = Integer.parseInt(Objects.requireNonNull(req.getParameter("page")));
        Specialty notAvailableSpecialty = service.getSpecialty(specialtyId);
        List<Subject> requiredSubjects = service.getRequiredSubjects(specialtyId);
        Page<Specialty> specialties = service.getSpecialties(universityId, PageRequest.of(page, RECORDS_PER_PAGE));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(AttributeNames.PAGE, page + 1);
        modelAndView.addObject(AttributeNames.NUMBER_OF_PAGES, specialties.getTotalPages());
        modelAndView.addObject(AttributeNames.NOT_AVAILABLE_SPECIALTY, notAvailableSpecialty);
        modelAndView.addObject(AttributeNames.REQUIRED_SUBJECTS, requiredSubjects);
        modelAndView.addObject(AttributeNames.SPECIALTIES, specialties.getContent());
        modelAndView.setViewName(Path.SPECIALTY_SELECTION_PAGE);
        return modelAndView;
    }
}
