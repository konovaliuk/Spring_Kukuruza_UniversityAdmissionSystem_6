package ua.company.spring.SpringUniversityAdmissionSystem.util;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Path {
    public static final String ACCESS_DENIED_PAGE;
    public static final String ADMIN_PAGE;
    public static final String CHECK_STATUS_PAGE;
    public static final String EXAM_PAGE;
    public static final String INDEX_PAGE;
    public static final String REGISTRATION_PAGE;
    public static final String RESULTS_PAGE;
    public static final String SEND_NOTIFICATION_PAGE;
    public static final String SET_GRADE_PAGE;
    public static final String SIGN_IN_PAGE;
    public static final String SPECIALTY_SELECTION_PAGE;
    public static final String SUMMARY_RATING_PAGE;
    public static final String UNIVERSITY_SELECTION_PAGE;

    static {
        Properties properties = new Properties();
        String fileName = "path.properties";
        try {
            properties.load(Objects.requireNonNull(Path.class.getClassLoader().getResourceAsStream(fileName)));
            ACCESS_DENIED_PAGE = properties.getProperty("accessDeniedPage");
            ADMIN_PAGE = properties.getProperty("adminPage");
            CHECK_STATUS_PAGE = properties.getProperty("checkStatusPage");
            EXAM_PAGE = properties.getProperty("examPage");
            INDEX_PAGE = properties.getProperty("indexPage");
            REGISTRATION_PAGE = properties.getProperty("registrationPage");
            RESULTS_PAGE = properties.getProperty("resultsPage");
            SEND_NOTIFICATION_PAGE = properties.getProperty("sendNotificationPage");
            SET_GRADE_PAGE = properties.getProperty("setGradePage");
            SIGN_IN_PAGE = properties.getProperty("signInPage");
            SPECIALTY_SELECTION_PAGE = properties.getProperty("specialtySelectionPage");
            SUMMARY_RATING_PAGE = properties.getProperty("summaryRatingPage");
            UNIVERSITY_SELECTION_PAGE = properties.getProperty("universitySelectionPage");
        } catch (IOException e) {
            throw new RuntimeException("Can't load " + fileName, e);
        }
    }
}
