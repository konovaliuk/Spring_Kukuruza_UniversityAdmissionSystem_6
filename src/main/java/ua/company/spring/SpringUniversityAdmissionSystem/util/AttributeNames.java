package ua.company.spring.SpringUniversityAdmissionSystem.util;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class AttributeNames {
    public static final String AVAILABLE_EXAMS;
    public static final String CHOSEN_SPECIALTY;
    public static final String FIRST_NAME;
    public static final String MESSAGE;
    public static final String NOT_AVAILABLE_SPECIALTY;
    public static final String NUMBER_OF_PAGES;
    public static final String PAGE;
    public static final String REGISTRATION_ERROR;
    public static final String REQUESTS;
    public static final String REQUIRED_SUBJECTS;
    public static final String SECOND_NAME;
    public static final String SIGN_IN_ERROR;
    public static final String SPECIALTIES;
    public static final String SUBJECTS;
    public static final String SUCCESS;
    public static final String UNIVERSITIES;
    public static final String USER;
    public static final String USER_EXAMS;
    public static final String USER_GRADES;
    public static final String USERS;

    static {
        Properties properties = new Properties();
        String fileName = "attributeName.properties";
        try {
            properties.load(Objects.requireNonNull(AttributeNames.class.getClassLoader().getResourceAsStream(fileName)));
            AVAILABLE_EXAMS = properties.getProperty("availableExams");
            CHOSEN_SPECIALTY = properties.getProperty("chosenSpecialty");
            FIRST_NAME = properties.getProperty("firstName");
            MESSAGE = properties.getProperty("message");
            NOT_AVAILABLE_SPECIALTY = properties.getProperty("notAvailableSpecialty");
            NUMBER_OF_PAGES = properties.getProperty("numberOfPages");
            PAGE = properties.getProperty("page");
            REGISTRATION_ERROR = properties.getProperty("registrationError");
            REQUESTS = properties.getProperty("requests");
            REQUIRED_SUBJECTS = properties.getProperty("requiredSubjects");
            SECOND_NAME = properties.getProperty("secondName");
            SIGN_IN_ERROR = properties.getProperty("signInError");
            SPECIALTIES = properties.getProperty("specialties");
            SUBJECTS = properties.getProperty("subjects");
            SUCCESS = properties.getProperty("success");
            UNIVERSITIES = properties.getProperty("universities");
            USER = properties.getProperty("user");
            USER_EXAMS = properties.getProperty("userExams");
            USER_GRADES = properties.getProperty("userGrades");
            USERS = properties.getProperty("users");
        } catch (IOException e) {
            throw new RuntimeException("Can't load " + fileName, e);
        }
    }
}
