package ua.company.spring.SpringUniversityAdmissionSystem.presentation.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.util.AttributeNames;
import ua.company.spring.SpringUniversityAdmissionSystem.util.Path;
import ua.company.spring.SpringUniversityAdmissionSystem.util.UserTypes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@Log4j2
public class NavBarController {
    @GetMapping("/index")
    public String index() {
        log.info("Try to get index page");
        return Path.INDEX_PAGE;
    }

    @GetMapping("/localization")
    public String localization(HttpSession session) {
        log.info("Changing a language");
        User user = (User) session.getAttribute(AttributeNames.USER);
        if (Objects.nonNull(user) && user.getUserType().getId() == UserTypes.ADMIN.getId()) {
            return Path.ADMIN_PAGE;
        }
        return Path.INDEX_PAGE;
    }

    @GetMapping("/signOut")
    public String signOut(HttpSession session) {
        log.info("Sign out");
        session.invalidate();
        return Path.INDEX_PAGE;
    }
}
