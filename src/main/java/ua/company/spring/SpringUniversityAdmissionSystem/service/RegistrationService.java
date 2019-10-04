package ua.company.spring.SpringUniversityAdmissionSystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUser;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUserStatus;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUserType;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.service.exception.RegistrationException;
import ua.company.spring.SpringUniversityAdmissionSystem.util.UserStatuses;
import ua.company.spring.SpringUniversityAdmissionSystem.util.UserTypes;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class RegistrationService {
    private final IDaoUser daoUser;
    private final IDaoUserType daoUserType;
    private final IDaoUserStatus daoUserStatus;

    public User register(User user) {
        user.setLogin(user.getLogin().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassportCode(user.getPassportCode().toLowerCase());
        user.setUserType(daoUserType.find(UserTypes.STUDENT.getId()).orElseThrow(RuntimeException::new));
        user.setUserStatus(daoUserStatus.find(UserStatuses.UNKNOWN.getId()).orElseThrow(RuntimeException::new));

        Optional<User> userWithSameLogin = daoUser.findByLogin(user.getLogin());
        if (userWithSameLogin.isPresent())
            throw new RegistrationException("User with the same login is already exist. Please choose another one.");

        Optional<User> userWithSameEmail = daoUser.findByEmail(user.getEmail());
        if (userWithSameEmail.isPresent())
            throw new RegistrationException("User with the same email is already exist. Please choose another one.");

        Optional<User> userWithSamePassportCode = daoUser.findByPassportCode(user.getPassportCode());
        if (userWithSamePassportCode.isPresent())
            throw new RegistrationException("User with the same passport code is already exist.");

        return daoUser.save(user);
    }
}
