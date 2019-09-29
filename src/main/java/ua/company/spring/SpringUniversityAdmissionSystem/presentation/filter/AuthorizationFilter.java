package ua.company.spring.SpringUniversityAdmissionSystem.presentation.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.UserType;
import ua.company.spring.SpringUniversityAdmissionSystem.util.AttributeNames;
import ua.company.spring.SpringUniversityAdmissionSystem.util.Path;
import ua.company.spring.SpringUniversityAdmissionSystem.util.UserTypes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Component
@Order(2)
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String securePath = req.getContextPath() + "/admin";
        boolean isSecurePageRequest = req.getRequestURI().startsWith(securePath);

        if (isSecurePageRequest) {
            if (isUserAdmin(req, resp)) {
                chain.doFilter(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher(Path.ACCESS_DENIED_PAGE);
                dispatcher.forward(req, response);
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

    private boolean isUserAdmin(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        boolean isLoggedIn = Objects.nonNull(session) && Objects.nonNull(session.getAttribute(AttributeNames.USER));
        if (isLoggedIn) {
            User user = (User) session.getAttribute(AttributeNames.USER);
            UserType userType = user.getUserType();
            return userType.getId() == UserTypes.ADMIN.getId();
        } else {
            return false;
        }
    }
}
