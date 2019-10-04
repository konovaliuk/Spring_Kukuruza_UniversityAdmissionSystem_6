package ua.company.spring.SpringUniversityAdmissionSystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoGrade;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Grade;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;

import java.util.List;

@Service
@Transactional
public class ResultsService {
    private IDaoGrade daoGrade;

    public ResultsService(IDaoGrade daoGrade) {
        this.daoGrade = daoGrade;
    }

    @Transactional(readOnly = true)
    public List<Grade> getUserGrades(User user) {
        return daoGrade.findByUser(user);
    }
}
