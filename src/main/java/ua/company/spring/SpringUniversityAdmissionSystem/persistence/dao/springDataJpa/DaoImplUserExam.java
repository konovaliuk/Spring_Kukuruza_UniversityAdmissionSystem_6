package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.springDataJpa;

import org.springframework.stereotype.Repository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUserExam;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Exam;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.User;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.UserExam;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository.IUserExamRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class DaoImplUserExam implements IDaoUserExam {
    private IUserExamRepository repository;

    public DaoImplUserExam(IUserExamRepository repository) {
        this.repository = repository;
    }

    @Override
    public void delete(UserExam entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteInBatch(List<UserExam> userExams) {
        repository.deleteInBatch(userExams);
    }

    @Override
    public Optional<UserExam> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<UserExam> findAll() {
        return repository.findAll();
    }

    @Override
    public List<UserExam> findByExamInAndUser(List<Exam> exams, User user) {
        return repository.findByExamInAndUser(exams, user);
    }

    @Override
    public List<UserExam> findByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    public UserExam save(UserExam entity) {
        return repository.save(entity);
    }

    @Override
    public List<UserExam> saveAll(List<UserExam> userExams) {
        return repository.saveAll(userExams);
    }

    @Override
    public UserExam update(UserExam entity) {
        return repository.save(entity);
    }
}
