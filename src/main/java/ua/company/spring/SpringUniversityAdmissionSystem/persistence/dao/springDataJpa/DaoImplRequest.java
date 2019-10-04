package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.springDataJpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoRequest;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.EducationOption;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Request;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository.IRequestRepository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DaoImplRequest implements IDaoRequest {
    private final IRequestRepository repository;

    @Override
    public void delete(Request request) {
        repository.delete(request);
    }

    @Override
    public Optional<Request> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Request> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Request> findByEducationOption(EducationOption educationOption) {
        return repository.findByEducationOption(educationOption);
    }

    @Override
    public Optional<Request> findByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    public Request save(Request entity) {
        return repository.save(entity);
    }

    @Override
    public Request update(Request entity) {
        return repository.save(entity);
    }
}
