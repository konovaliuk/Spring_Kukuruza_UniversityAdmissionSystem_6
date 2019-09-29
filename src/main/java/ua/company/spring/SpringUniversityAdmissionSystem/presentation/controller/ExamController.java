package ua.company.spring.SpringUniversityAdmissionSystem.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Exam;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.service.ExamService;
import ua.company.spring.SpringUniversityAdmissionSystem.util.AttributeNames;
import ua.company.spring.SpringUniversityAdmissionSystem.util.Path;

import java.util.List;
import java.util.Objects;

@Controller
@SessionAttributes("user")
public class ExamController {
    private ExamService service;

    public ExamController(ExamService service) {
        this.service = service;
    }

    @GetMapping("/exam")
    public String getExamPage(@ModelAttribute User user, ModelMap model) {
        List<Exam> userExams = service.getUserExams(user);
        List<Exam> availableExams = service.getAvailableExams(userExams);
        model.addAttribute(AttributeNames.USER_EXAMS, userExams);
        model.addAttribute(AttributeNames.AVAILABLE_EXAMS, availableExams);
        return Path.EXAM_PAGE;
    }

    @PostMapping("/examRegistration")
    public String examRegistration(@RequestParam(value = "exam_id", required = false) String[] examsId,
                                   @ModelAttribute User user) {
        if (Objects.nonNull(examsId) && examsId.length > 0) {
            service.registerUserToExams(user, examsId);
        }
        return "redirect:/exam";
    }

    @PostMapping("/examCancelRegistration")
    public String examCancelRegistration(@RequestParam(value = "exam_id", required = false) String[] examsId,
                                         @ModelAttribute User user) {
        if (Objects.nonNull(examsId) && examsId.length > 0) {
            service.cancelRegistrationUserToExams(user, examsId);
        }
        return "redirect:/exam";
    }
}
