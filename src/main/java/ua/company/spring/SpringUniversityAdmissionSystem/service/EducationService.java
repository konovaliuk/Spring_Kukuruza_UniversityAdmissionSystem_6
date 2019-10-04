package ua.company.spring.SpringUniversityAdmissionSystem.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.*;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.*;
import ua.company.spring.SpringUniversityAdmissionSystem.service.exception.SubmitSpecialtyException;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class EducationService {
    private final IDaoEducationOption daoEducationOption;
    private final IDaoGrade daoGrade;
    private final IDaoRequest daoRequest;
    private final IDaoSpecialty daoSpecialty;
    private final IDaoSpecialtySubject daoSpecialtySubject;
    private final IDaoUniversity daoUniversity;

    @Transactional(readOnly = true)
    public Optional<Specialty> getChosenSpecialty(User user) {
        Optional<Request> userRequest = daoRequest.findByUser(user);
        return userRequest.map(request -> request.getEducationOption().getSpecialty());
    }

    @Transactional(readOnly = true)
    public Page<University> getUniversities(Pageable pageable) {
        return daoUniversity.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Specialty> getSpecialties(Integer universityId, Pageable pageable) {
        University university = daoUniversity.find(universityId).orElseThrow(RuntimeException::new);
        Page<EducationOption> educationOptions = daoEducationOption.findByUniversity(university, pageable);
        return educationOptions.map(EducationOption::getSpecialty);
    }

    @Transactional(readOnly = true)
    public Specialty getSpecialty(Integer specialtyId) {
        return daoSpecialty.find(specialtyId).orElseThrow(RuntimeException::new);
    }

    @Transactional(readOnly = true)
    public List<Subject> getRequiredSubjects(Integer specialtyId) {
        Set<SpecialtySubject> specialtySubjects = daoSpecialtySubject.findBySpecialtyId(specialtyId);
        return specialtySubjects.stream()
                .map(SpecialtySubject::getSubject)
                .collect(Collectors.toList());
    }

    public void dropUserSpecialtyRequest(User user) {
        Optional<Request> request = daoRequest.findByUser(user);
        request.ifPresent(daoRequest::delete);
    }

    public Specialty submitRequest(User user, Integer universityId, Integer specialtyId) {
        Integer rating = getRatingByRequiredSubjects(user, specialtyId);
        Optional<EducationOption> educationOption =
                daoEducationOption.findByUniversityIdAndSpecialtyId(universityId, specialtyId);
        Request request = Request.builder()
                .user(user)
                .rating(rating)
                .educationOption(educationOption.orElseThrow(RuntimeException::new))
                .build();
        Request saved = daoRequest.save(request);
        return saved.getEducationOption().getSpecialty();
    }

    private Integer getRatingByRequiredSubjects(User user, Integer specialtyId) {
        Set<Subject> subjects = daoSpecialtySubject.findBySpecialtyId(specialtyId)
                .stream()
                .map(SpecialtySubject::getSubject)
                .collect(Collectors.toSet());
        int rating = 0;
        for (Subject subject : subjects) {
            Optional<Grade> grade = daoGrade.findByUserAndSubject(user, subject);
            if (grade.isPresent()) {
                rating += grade.get().getResult();
            } else {
                throw new SubmitSpecialtyException("No grade");
            }
        }
        return rating;
    }
}
