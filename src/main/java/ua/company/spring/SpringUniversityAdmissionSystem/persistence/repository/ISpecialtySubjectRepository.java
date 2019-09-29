package ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.SpecialtySubject;

import java.util.Set;

public interface ISpecialtySubjectRepository extends JpaRepository<SpecialtySubject, Long> {
    Set<SpecialtySubject> findBySpecialtyId(Integer specialtyId);
}
