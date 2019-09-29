package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.University;

public interface IDaoUniversity extends IDaoGeneric<University, Integer> {
    Page<University> findAll(Pageable pageable);
}
