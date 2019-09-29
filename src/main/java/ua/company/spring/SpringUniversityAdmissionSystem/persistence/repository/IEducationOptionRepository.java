package ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.EducationOption;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.University;

import java.util.Optional;

public interface IEducationOptionRepository extends JpaRepository<EducationOption, Long> {
    Page<EducationOption> findByUniversity(University university, Pageable pageable);

    Optional<EducationOption> findByUniversityIdAndSpecialtyId(Integer universityId, Integer specialtyId);
}
