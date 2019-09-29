package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao;

import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.SpecialtySubject;

import java.util.Set;

public interface IDaoSpecialtySubject extends IDaoGeneric<SpecialtySubject, Long> {
    Set<SpecialtySubject> findBySpecialtyId(Integer specialtyId);
}
