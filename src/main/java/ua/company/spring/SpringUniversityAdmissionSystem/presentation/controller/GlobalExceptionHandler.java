package ua.company.spring.SpringUniversityAdmissionSystem.presentation.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ua.company.spring.SpringUniversityAdmissionSystem.util.AttributeNames;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Throwable.class)
    public ModelAndView handleAnyError(Throwable e) {
        ModelAndView modelAndView = new ModelAndView("/errorPages/error");
        modelAndView.addObject(AttributeNames.MESSAGE, e.getMessage());
        return modelAndView;
    }
}
