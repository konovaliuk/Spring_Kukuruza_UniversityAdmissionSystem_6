package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.springDataJpa;

import org.springframework.stereotype.Repository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoGrade;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Grade;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Subject;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository.IGradeRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class DaoImplGrade implements IDaoGrade {
    private IGradeRepository repository;

    public DaoImplGrade(IGradeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void delete(Grade entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<Grade> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Grade> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Grade> findByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    public Optional<Grade> findByUserAndSubject(User user, Subject subject) {
        return repository.findByUserAndSubject(user, subject);
    }

    @Override
    public Grade save(Grade entity) {
        return repository.save(entity);
    }

    @Override
    public Grade update(Grade entity) {
        return repository.save(entity);
    }
}
