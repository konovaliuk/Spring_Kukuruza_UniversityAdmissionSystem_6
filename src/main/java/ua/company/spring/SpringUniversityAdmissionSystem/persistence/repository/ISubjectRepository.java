package ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Subject;

public interface ISubjectRepository extends JpaRepository<Subject, Integer> {
}
