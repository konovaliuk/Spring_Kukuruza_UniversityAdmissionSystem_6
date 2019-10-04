package ua.company.spring.SpringUniversityAdmissionSystem.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUser;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.service.exception.SignInException;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class AuthenticationService {
    private final IDaoUser daoUser;

    @Transactional(readOnly = true)
    public User signIn(String login, String password) {
        log.info("Try to sign in");
        if (Objects.isNull(login) || Objects.isNull(password))
            throw new SignInException("Please fill all of the fields of the form!");

        Optional<User> user = daoUser.findByLogin(login);
        return user.filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new SignInException("Oops, wrong login or password, try again!"));
    }
}
