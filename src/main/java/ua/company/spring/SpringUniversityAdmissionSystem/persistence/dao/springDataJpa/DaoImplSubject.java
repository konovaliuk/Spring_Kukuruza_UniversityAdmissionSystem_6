package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.springDataJpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoSubject;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Subject;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository.ISubjectRepository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DaoImplSubject implements IDaoSubject {
    private final ISubjectRepository repository;

    @Override
    public void delete(Subject entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<Subject> find(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Subject> findAll() {
        return repository.findAll();
    }

    @Override
    public Subject save(Subject entity) {
        return repository.save(entity);
    }

    @Override
    public Subject update(Subject entity) {
        return repository.save(entity);
    }
}
