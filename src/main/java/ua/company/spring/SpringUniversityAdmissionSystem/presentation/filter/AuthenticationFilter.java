package ua.company.spring.SpringUniversityAdmissionSystem.presentation.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ua.company.spring.SpringUniversityAdmissionSystem.util.AttributeNames;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String rootURI = req.getContextPath() + "/";
        String indexURI = req.getContextPath() + "/index";
        String registrationURI = req.getContextPath() + "/registration";
        String localizationURI = req.getContextPath() + "/localization";
        String signInURI = req.getContextPath() + "/signIn";

        boolean isLoggedIn = Objects.nonNull(session) && Objects.nonNull(session.getAttribute(AttributeNames.USER));
        boolean isRootRequest = req.getRequestURI().equals(rootURI);
        boolean isIndexRequest = req.getRequestURI().equals(indexURI);
        boolean isRegistrationRequest = req.getRequestURI().equals(registrationURI);
        boolean isLocalizationRequest = req.getRequestURI().equals(localizationURI);
        boolean isSignInRequest = req.getRequestURI().equals(signInURI);

        if (isLoggedIn) {
            if (isRegistrationRequest || isSignInRequest) {
                resp.sendRedirect(indexURI);
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            if (isIndexRequest || isRegistrationRequest || isRootRequest || isSignInRequest || isLocalizationRequest) {
                chain.doFilter(req, resp);
            } else {
                resp.sendRedirect(signInURI);
            }
        }
    }
}
