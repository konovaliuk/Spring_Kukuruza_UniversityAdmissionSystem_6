package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.springDataJpa;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoUniversity;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.University;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository.IUniversityRepository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DaoImplUniversity implements IDaoUniversity {
    private final IUniversityRepository repository;

    @Override
    public void delete(University entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<University> find(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<University> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<University> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public University save(University entity) {
        return repository.save(entity);
    }

    @Override
    public University update(University entity) {
        return repository.save(entity);
    }
}
