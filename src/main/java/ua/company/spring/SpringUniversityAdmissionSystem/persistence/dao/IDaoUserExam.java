package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao;

import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Exam;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.UserExam;

import java.util.List;

public interface IDaoUserExam extends IDaoGeneric<UserExam, Long> {
    List<UserExam> findByUser(User user);

    List<UserExam> findByExamInAndUser(List<Exam> exams, User user);

    List<UserExam> saveAll(List<UserExam> userExams);

    void deleteInBatch(List<UserExam> userExams);
}
