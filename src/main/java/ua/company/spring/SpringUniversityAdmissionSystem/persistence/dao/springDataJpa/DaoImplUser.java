package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.springDataJpa;

import org.springframework.stereotype.Repository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUser;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository.IUserRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class DaoImplUser implements IDaoUser {
    private IUserRepository repository;

    public DaoImplUser(IUserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public void delete(User entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<User> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<User> findByFirstNameAndSecondName(String firstName, String secondName) {
        return repository.findByFirstNameAndSecondName(firstName, secondName);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Optional<User> findByPassportCode(String passportCode) {
        return repository.findByPassportCode(passportCode);
    }

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public User update(User entity) {
        return repository.save(entity);
    }
}
