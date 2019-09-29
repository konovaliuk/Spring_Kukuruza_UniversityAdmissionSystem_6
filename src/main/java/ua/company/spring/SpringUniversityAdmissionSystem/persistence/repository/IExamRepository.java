package ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Exam;

import java.util.List;
import java.util.Set;

public interface IExamRepository extends JpaRepository<Exam, Integer> {
    List<Exam> findByIdNotIn(Set<Integer> id);

    List<Exam> findByIdIn(Set<Integer> id);
}
