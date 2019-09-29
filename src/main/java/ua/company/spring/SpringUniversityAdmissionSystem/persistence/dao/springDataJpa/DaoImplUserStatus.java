package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.springDataJpa;

import org.springframework.stereotype.Repository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUserStatus;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.UserStatus;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository.IUserStatusRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class DaoImplUserStatus implements IDaoUserStatus {
    private IUserStatusRepository repository;

    public DaoImplUserStatus(IUserStatusRepository userStatusRepository) {
        this.repository = userStatusRepository;
    }

    @Override
    public void delete(UserStatus entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<UserStatus> find(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<UserStatus> findAll() {
        return repository.findAll();
    }

    @Override
    public UserStatus save(UserStatus entity) {
        return repository.save(entity);
    }

    @Override
    public UserStatus update(UserStatus entity) {
        return repository.save(entity);
    }
}
