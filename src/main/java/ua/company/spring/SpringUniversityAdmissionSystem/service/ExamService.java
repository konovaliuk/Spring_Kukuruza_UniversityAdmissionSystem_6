package ua.company.spring.SpringUniversityAdmissionSystem.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoExam;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUserExam;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Exam;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.UserExam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class ExamService {
    private final IDaoUserExam daoUserExam;
    private final IDaoExam daoExam;

    @Transactional(readOnly = true)
    public List<Exam> getUserExams(User user) {
        log.info("Try to get a list of user exams");
        List<UserExam> userExams = daoUserExam.findByUser(user);
        return userExams.stream()
                .map(UserExam::getExam)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Exam> getAvailableExams(List<Exam> userExams) {
        log.info("Try to get a list of user available exams");
        if (userExams.isEmpty())
            return daoExam.findAll();
        Set<Integer> id = userExams.stream().map(Exam::getId).collect(Collectors.toSet());
        return daoExam.findByIdNotIn(id);
    }

    public void cancelRegistrationUserToExams(User user, String[] examsId) {
        log.info("Try to cancel user registration to exams");
        Set<Integer> examsIdSet = convertToSet(examsId);
        List<Exam> exams = daoExam.findByIdIn(examsIdSet);
        List<UserExam> userExams = daoUserExam.findByExamInAndUser(exams, user);
        daoUserExam.deleteInBatch(userExams);
    }

    public void registerUserToExams(User user, String[] examsId) {
        log.info("Try to register user to exams");
        Set<Integer> examsIdSet = convertToSet(examsId);
        List<Exam> exams = daoExam.findByIdIn(examsIdSet);
        List<UserExam> userExams = createUserExams(user, exams);
        daoUserExam.saveAll(userExams);
    }

    private Set<Integer> convertToSet(String[] examsId) {
        Set<Integer> examsIdSet = new HashSet<>();
        for (String examId : examsId) {
            examsIdSet.add(Integer.valueOf(examId));
        }
        return examsIdSet;
    }

    private List<UserExam> createUserExams(User user, List<Exam> exams) {
        List<UserExam> userExams = new ArrayList<>();
        for (Exam exam : exams) {
            UserExam userExam = UserExam.builder()
                    .user(user)
                    .exam(exam)
                    .build();
            userExams.add(userExam);
        }
        return userExams;
    }
}
