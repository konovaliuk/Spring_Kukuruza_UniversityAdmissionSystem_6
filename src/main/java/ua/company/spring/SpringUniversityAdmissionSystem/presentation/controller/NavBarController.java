package ua.company.spring.SpringUniversityAdmissionSystem.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.util.AttributeNames;
import ua.company.spring.SpringUniversityAdmissionSystem.util.Path;
import ua.company.spring.SpringUniversityAdmissionSystem.util.UserTypes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class NavBarController {
    @GetMapping("/index")
    public String index() {
        return Path.INDEX_PAGE;
    }

    @GetMapping("/localization")
    public String localization(HttpSession session) {
        User user = (User) session.getAttribute(AttributeNames.USER);
        if (Objects.nonNull(user) && user.getUserType().getId() == UserTypes.ADMIN.getId()) {
            return Path.ADMIN_PAGE;
        }
        return Path.INDEX_PAGE;
    }

    @GetMapping("/signOut")
    public String signOut(HttpSession session) {
        session.invalidate();
        return Path.INDEX_PAGE;
    }
}
