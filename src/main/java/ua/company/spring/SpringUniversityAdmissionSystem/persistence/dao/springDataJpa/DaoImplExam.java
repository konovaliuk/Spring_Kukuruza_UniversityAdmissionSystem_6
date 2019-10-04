package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.springDataJpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoExam;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Exam;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository.IExamRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
public class DaoImplExam implements IDaoExam {
    private final IExamRepository repository;

    @Override
    public void delete(Exam entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<Exam> find(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Exam> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Exam> findByIdIn(Set<Integer> id) {
        return repository.findByIdIn(id);
    }

    @Override
    public List<Exam> findByIdNotIn(Set<Integer> id) {
        return repository.findByIdNotIn(id);
    }

    @Override
    public Exam save(Exam entity) {
        return repository.save(entity);
    }

    @Override
    public Exam update(Exam entity) {
        return repository.save(entity);
    }
}
