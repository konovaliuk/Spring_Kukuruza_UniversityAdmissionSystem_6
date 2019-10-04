package ua.company.spring.SpringUniversityAdmissionSystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUser;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;

@Service
@Transactional
public class CheckStatusService {
    private IDaoUser daoUser;

    public CheckStatusService(IDaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @Transactional(readOnly = true)
    public User getUpdatedUser(User user) {
        return daoUser.find(user.getId()).orElseThrow(RuntimeException::new);
    }
}
