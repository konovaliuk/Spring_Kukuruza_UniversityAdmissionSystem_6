package ua.company.spring.SpringUniversityAdmissionSystem.service;

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
public class ExamService {
    private IDaoUserExam daoUserExam;
    private IDaoExam daoExam;

    public ExamService(IDaoUserExam daoUserExam, IDaoExam daoExam) {
        this.daoUserExam = daoUserExam;
        this.daoExam = daoExam;
    }

    @Transactional(readOnly = true)
    public List<Exam> getUserExams(User user) {
        List<UserExam> userExams = daoUserExam.findByUser(user);
        return userExams.stream()
                .map(UserExam::getExam)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Exam> getAvailableExams(List<Exam> userExams) {
        if (userExams.isEmpty())
            return daoExam.findAll();
        Set<Integer> id = userExams.stream().map(Exam::getId).collect(Collectors.toSet());
        return daoExam.findByIdNotIn(id);
    }

    public void cancelRegistrationUserToExams(User user, String[] examsId) {
        Set<Integer> examsIdSet = convertToSet(examsId);
        List<Exam> exams = daoExam.findByIdIn(examsIdSet);
        List<UserExam> userExams = daoUserExam.findByExamInAndUser(exams, user);
        daoUserExam.deleteInBatch(userExams);
    }

    public void registerUserToExams(User user, String[] examsId) {
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
            UserExam userExam = new UserExam.Builder()
                    .setUser(user)
                    .setExam(exam)
                    .build();
            userExams.add(userExam);
        }
        return userExams;
    }
}
