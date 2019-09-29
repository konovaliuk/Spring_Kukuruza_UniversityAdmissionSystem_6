package ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Grade;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Subject;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;

import java.util.List;
import java.util.Optional;

public interface IGradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByUser(User user);

    Optional<Grade> findByUserAndSubject(User user, Subject subject);
}
