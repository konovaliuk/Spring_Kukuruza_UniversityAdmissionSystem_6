package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.springDataJpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoSpecialtySubject;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.SpecialtySubject;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository.ISpecialtySubjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
public class DaoImplSpecialtySubject implements IDaoSpecialtySubject {
    private final ISpecialtySubjectRepository repository;

    @Override
    public void delete(SpecialtySubject entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<SpecialtySubject> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<SpecialtySubject> findAll() {
        return repository.findAll();
    }

    @Override
    public Set<SpecialtySubject> findBySpecialtyId(Integer specialtyId) {
        return repository.findBySpecialtyId(specialtyId);
    }

    @Override
    public SpecialtySubject save(SpecialtySubject entity) {
        return repository.save(entity);
    }

    @Override
    public SpecialtySubject update(SpecialtySubject entity) {
        return repository.save(entity);
    }
}
