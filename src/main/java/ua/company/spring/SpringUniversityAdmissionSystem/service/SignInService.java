package ua.company.spring.SpringUniversityAdmissionSystem.service;

import org.springframework.stereotype.Service;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUser;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.service.exception.SignInException;

import java.util.Objects;
import java.util.Optional;

@Service
public class SignInService {
    private IDaoUser daoUser;

    public SignInService(IDaoUser daoUser) {
        this.daoUser = daoUser;
    }

    public User signIn(String login, String password) {
        if (Objects.isNull(login) || Objects.isNull(password))
            throw new SignInException("Please fill all of the fields of the form!");

        Optional<User> user = daoUser.findByLogin(login);
        return user.filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new SignInException("Oops, wrong login or password, try again!"));
    }
}
