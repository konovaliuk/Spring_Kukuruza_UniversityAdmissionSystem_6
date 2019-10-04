package ua.company.spring.SpringUniversityAdmissionSystem.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class EducationService {
    private final IDaoEducationOption daoEducationOption;
    private final IDaoGrade daoGrade;
    private final IDaoRequest daoRequest;
    private final IDaoSpecialty daoSpecialty;
    private final IDaoSpecialtySubject daoSpecialtySubject;
    private final IDaoUniversity daoUniversity;

    @Transactional(readOnly = true)
    public Optional<Specialty> getChosenSpecialty(User user) {
        log.info("Try to get user chosen specialty");
        Optional<Request> userRequest = daoRequest.findByUser(user);
        return userRequest.map(request -> request.getEducationOption().getSpecialty());
    }

    @Transactional(readOnly = true)
    public Page<University> getUniversities(Pageable pageable) {
        log.info("Try to find a list of universities for a page");
        return daoUniversity.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Specialty> getSpecialties(Integer universityId, Pageable pageable) {
        log.info("Try to find a list of specialties for a page");
        University university = daoUniversity.find(universityId).orElseThrow(RuntimeException::new);
        Page<EducationOption> educationOptions = daoEducationOption.findByUniversity(university, pageable);
        return educationOptions.map(EducationOption::getSpecialty);
    }

    @Transactional(readOnly = true)
    public Specialty getSpecialty(Integer specialtyId) {
        log.info("Try to find a specialty by id");
        return daoSpecialty.find(specialtyId).orElseThrow(RuntimeException::new);
    }

    @Transactional(readOnly = true)
    public List<Subject> getRequiredSubjects(Integer specialtyId) {
        log.info("Try to find a list of required subjects");
        Set<SpecialtySubject> specialtySubjects = daoSpecialtySubject.findBySpecialtyId(specialtyId);
        return specialtySubjects.stream()
                .map(SpecialtySubject::getSubject)
                .collect(Collectors.toList());
    }

    public void dropUserSpecialtyRequest(User user) {
        log.info("Try to drop user request");
        Optional<Request> request = daoRequest.findByUser(user);
        request.ifPresent(daoRequest::delete);
    }

    public Specialty submitRequest(User user, Integer universityId, Integer specialtyId) {
        log.info("Try to submit user request");
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
        log.info("Try to get rating by required subjects");
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
        log.info("Rating by required subjects was successfully calculated");
        return rating;
    }
}
