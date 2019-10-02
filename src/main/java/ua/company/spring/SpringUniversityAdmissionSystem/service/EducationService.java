package ua.company.spring.SpringUniversityAdmissionSystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.*;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.*;
import ua.company.spring.SpringUniversityAdmissionSystem.service.exception.SubmitSpecialtyException;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EducationService {
    private IDaoEducationOption daoEducationOption;
    private IDaoGrade daoGrade;
    private IDaoRequest daoRequest;
    private IDaoSpecialty daoSpecialty;
    private IDaoSpecialtySubject daoSpecialtySubject;
    private IDaoUniversity daoUniversity;

    public EducationService(IDaoEducationOption daoEducationOption, IDaoGrade daoGrade,
                            IDaoRequest daoRequest, IDaoSpecialty daoSpecialty,
                            IDaoSpecialtySubject daoSpecialtySubject, IDaoUniversity daoUniversity) {
        this.daoEducationOption = daoEducationOption;
        this.daoGrade = daoGrade;
        this.daoRequest = daoRequest;
        this.daoSpecialty = daoSpecialty;
        this.daoSpecialtySubject = daoSpecialtySubject;
        this.daoUniversity = daoUniversity;
    }

    public Optional<Specialty> getChosenSpecialty(User user) {
        Optional<Request> userRequest = daoRequest.findByUser(user);
        return userRequest.map(request -> request.getEducationOption().getSpecialty());
    }

    public Page<University> getUniversities(Pageable pageable) {
        return daoUniversity.findAll(pageable);
    }

    public Page<Specialty> getSpecialties(Integer universityId, Pageable pageable) {
        University university = daoUniversity.find(universityId).orElseThrow(RuntimeException::new);
        Page<EducationOption> educationOptions = daoEducationOption.findByUniversity(university, pageable);
        return educationOptions.map(EducationOption::getSpecialty);
    }

    public Integer getRatingByRequiredSubjects(User user, Integer specialtyId) {
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

    public Specialty submitRequest(User user, Integer rating, Integer universityId, Integer specialtyId) {
        Optional<EducationOption> educationOption =
                daoEducationOption.findByUniversityIdAndSpecialtyId(universityId, specialtyId);
        Request request = new Request.Builder()
                .setUser(user)
                .setRating(rating)
                .setEducationOption(educationOption.orElseThrow(RuntimeException::new))
                .build();
        Request saved = daoRequest.save(request);
        return saved.getEducationOption().getSpecialty();
    }

    public Specialty getSpecialty(Integer specialtyId) {
        return daoSpecialty.find(specialtyId).orElseThrow(RuntimeException::new);
    }

    public List<Subject> getRequiredSubjects(Integer specialtyId) {
        Set<SpecialtySubject> specialtySubjects = daoSpecialtySubject.findBySpecialtyId(specialtyId);
        return specialtySubjects.stream()
                .map(SpecialtySubject::getSubject)
                .collect(Collectors.toList());
    }

    public void dropUserSpecialtyRequest(User user) {
        Optional<Request> request = daoRequest.findByUser(user);
        request.ifPresent(r -> daoRequest.delete(r));
    }
}
