package ua.company.spring.SpringUniversityAdmissionSystem.presentation.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Subject;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.service.AdminService;
import ua.company.spring.SpringUniversityAdmissionSystem.util.AttributeNames;
import ua.company.spring.SpringUniversityAdmissionSystem.util.Path;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService service;

    @GetMapping("/adminPage")
    public String adminPage() {
        return Path.ADMIN_PAGE;
    }

    @GetMapping("/setGrade")
    public String setGradePage() {
        return Path.SET_GRADE_PAGE;
    }

    @GetMapping("/sendNotification")
    public String sendNotificationPage() {
        return Path.SEND_NOTIFICATION_PAGE;
    }

    @PostMapping("/findUserSettingGrade")
    public String findUserSettingGrade(@RequestParam("first_name") String firstName,
                                       @RequestParam("second_name") String secondName,
                                       ModelMap model) {
        List<User> users = service.findUsers(firstName, secondName);
        if (users.isEmpty()) {
            model.addAttribute(AttributeNames.FIRST_NAME, firstName);
            model.addAttribute(AttributeNames.SECOND_NAME, secondName);
        } else {
            List<Subject> subjects = service.getAllSubjects();
            model.addAttribute(AttributeNames.SUBJECTS, subjects);
            model.addAttribute(AttributeNames.USERS, users);
        }
        return Path.SET_GRADE_PAGE;
    }

    @PostMapping("/setGrade")
    public String settingGrade(@RequestParam("subject_id") Integer subjectId,
                               @RequestParam("user_id") Long userId,
                               @RequestParam("result") Integer result,
                               ModelMap model) {
        service.setGrade(userId, subjectId, result);
        model.addAttribute(AttributeNames.SUCCESS, "success");
        return Path.SET_GRADE_PAGE;
    }

    @PostMapping("/findUserSendingNotification")
    public String findUserSendingNotification(@RequestParam("first_name") String firstName,
                                              @RequestParam("second_name") String secondName,
                                              ModelMap model) {
        List<User> users = service.findUsers(firstName, secondName);
        if (users.isEmpty()) {
            model.addAttribute(AttributeNames.FIRST_NAME, firstName);
            model.addAttribute(AttributeNames.SECOND_NAME, secondName);
        } else {
            model.addAttribute(AttributeNames.USERS, users);
        }
        return Path.SEND_NOTIFICATION_PAGE;
    }

    @PostMapping("/setUserStatus")
    public String sendingNotification(@RequestParam("user_id") Long userId,
                                      @RequestParam("userStatusId") Integer userStatusId,
                                      ModelMap model) {
        service.setUserStatus(userId, userStatusId);
        model.addAttribute(AttributeNames.SUCCESS, "success");
        return Path.SEND_NOTIFICATION_PAGE;
    }
}
