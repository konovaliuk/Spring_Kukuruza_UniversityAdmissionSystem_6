package ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.University;

public interface IUniversityRepository extends JpaRepository<University, Integer> {
    Page<University> findAll(Pageable pageable);
}
