package ua.company.spring.SpringUniversityAdmissionSystem.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoGrade;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Grade;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class ResultsService {
    private final IDaoGrade daoGrade;

    @Transactional(readOnly = true)
    public List<Grade> getUserGrades(User user) {
        log.info("Try to get user grades");
        return daoGrade.findByUser(user);
    }
}
