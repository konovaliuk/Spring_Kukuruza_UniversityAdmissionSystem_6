package ua.company.spring.SpringUniversityAdmissionSystem.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUser;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class CheckStatusService {
    private final IDaoUser daoUser;

    @Transactional(readOnly = true)
    public User getUpdatedUser(User user) {
        log.info("Try to get updated user");
        return daoUser.find(user.getId()).orElseThrow(RuntimeException::new);
    }
}
