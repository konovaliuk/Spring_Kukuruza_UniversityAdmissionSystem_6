package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.EducationOption;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.University;

import java.util.Optional;

public interface IDaoEducationOption extends IDaoGeneric<EducationOption, Long> {
    Page<EducationOption> findByUniversity(University university, Pageable pageable);

    Optional<EducationOption> findByUniversityIdAndSpecialtyId(Integer universityId, Integer specialtyId);
}
