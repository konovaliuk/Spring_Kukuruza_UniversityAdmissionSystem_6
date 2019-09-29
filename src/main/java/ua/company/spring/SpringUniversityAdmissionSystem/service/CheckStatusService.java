package ua.company.spring.SpringUniversityAdmissionSystem.service;

import org.springframework.stereotype.Service;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUser;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;

@Service
public class CheckStatusService {
    private IDaoUser daoUser;

    public CheckStatusService(IDaoUser daoUser) {
        this.daoUser = daoUser;
    }

    public User getUpdatedUser(User user) {
        return daoUser.find(user.getId()).orElseThrow(RuntimeException::new);
    }
}
