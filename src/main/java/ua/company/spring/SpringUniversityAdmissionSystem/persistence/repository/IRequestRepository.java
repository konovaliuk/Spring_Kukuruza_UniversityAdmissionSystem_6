package ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.EducationOption;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Request;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;

import java.util.List;
import java.util.Optional;

public interface IRequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByEducationOption(EducationOption educationOption);

    Optional<Request> findByUser(User user);
}
