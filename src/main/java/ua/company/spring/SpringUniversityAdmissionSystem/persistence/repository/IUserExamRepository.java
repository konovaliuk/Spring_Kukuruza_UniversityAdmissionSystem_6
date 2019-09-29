package ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Exam;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.UserExam;

import java.util.List;

public interface IUserExamRepository extends JpaRepository<UserExam, Long> {
    List<UserExam> findByUser(User user);

    List<UserExam> findByExamInAndUser(List<Exam> exams, User user);
}
