package ua.company.spring.SpringUniversityAdmissionSystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoGrade;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoSubject;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUser;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUserStatus;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Grade;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Subject;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.UserStatus;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AdminService {
    private final IDaoGrade daoGrade;
    private final IDaoSubject daoSubject;
    private final IDaoUser daoUser;
    private final IDaoUserStatus daoUserStatus;

    @Transactional(readOnly = true)
    public List<User> findUsers(String firstName, String secondName) {
        return daoUser.findByFirstNameAndSecondName(firstName, secondName);
    }

    @Transactional(readOnly = true)
    public List<Subject> getAllSubjects() {
        return daoSubject.findAll();
    }

    public void setGrade(Long userId, Integer subjectId, Integer result) {
        User user = daoUser.find(userId).orElseThrow(RuntimeException::new);
        Subject subject = daoSubject.find(subjectId).orElseThrow(RuntimeException::new);
        Optional<Grade> oldGrade = daoGrade.findByUserAndSubject(user, subject);
        if (oldGrade.isPresent()) {
            Grade grade = oldGrade.get();
            grade.setResult(result);
            daoGrade.update(grade);
        } else {
            Grade grade = Grade.builder()
                    .user(user)
                    .subject(subject)
                    .result(result)
                    .build();
            daoGrade.save(grade);
        }
    }

    public void setUserStatus(Long userId, Integer userStatusId) {
        User user = daoUser.find(userId).orElseThrow(RuntimeException::new);
        UserStatus userStatus = daoUserStatus.find(userStatusId).orElseThrow(RuntimeException::new);
        user.setUserStatus(userStatus);
        daoUser.update(user);
    }
}
