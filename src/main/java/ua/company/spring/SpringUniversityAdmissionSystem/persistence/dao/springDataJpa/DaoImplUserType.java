package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.springDataJpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUserType;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.UserType;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository.IUserTypeRepository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DaoImplUserType implements IDaoUserType {
    private final IUserTypeRepository repository;

    @Override
    public void delete(UserType entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<UserType> find(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<UserType> findAll() {
        return repository.findAll();
    }

    @Override
    public UserType save(UserType entity) {
        return repository.save(entity);
    }

    @Override
    public UserType update(UserType entity) {
        return repository.save(entity);
    }
}
