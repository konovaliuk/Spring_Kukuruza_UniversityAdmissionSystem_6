package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao;

import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.EducationOption;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Request;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;

import java.util.List;
import java.util.Optional;

public interface IDaoRequest extends IDaoGeneric<Request, Long> {
    List<Request> findByEducationOption(EducationOption educationOption);

    Optional<Request> findByUser(User user);
}
