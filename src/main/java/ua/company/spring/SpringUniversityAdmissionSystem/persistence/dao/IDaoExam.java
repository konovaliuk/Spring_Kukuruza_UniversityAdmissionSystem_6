package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao;

import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Exam;

import java.util.List;
import java.util.Set;

public interface IDaoExam extends IDaoGeneric<Exam, Integer> {
    List<Exam> findByIdNotIn(Set<Integer> id);

    List<Exam> findByIdIn(Set<Integer> id);
}
