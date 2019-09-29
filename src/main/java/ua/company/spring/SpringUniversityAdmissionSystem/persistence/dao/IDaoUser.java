package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao;

import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;

import java.util.List;
import java.util.Optional;

public interface IDaoUser extends IDaoGeneric<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByFirstNameAndSecondName(String firstName, String secondName);

    Optional<User> findByLogin(String login);

    Optional<User> findByPassportCode(String passportCode);
}
