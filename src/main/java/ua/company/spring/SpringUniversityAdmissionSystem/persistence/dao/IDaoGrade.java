package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao;

import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Grade;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Subject;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;

import java.util.List;
import java.util.Optional;

public interface IDaoGrade extends IDaoGeneric<Grade, Long> {
    List<Grade> findByUser(User user);

    Optional<Grade> findByUserAndSubject(User user, Subject subject);
}
