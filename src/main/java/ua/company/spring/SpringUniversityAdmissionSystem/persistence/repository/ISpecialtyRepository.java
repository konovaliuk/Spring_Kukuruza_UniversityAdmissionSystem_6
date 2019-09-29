package ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Specialty;

public interface ISpecialtyRepository extends JpaRepository<Specialty, Integer> {
}
