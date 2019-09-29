package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.springDataJpa;

import org.springframework.stereotype.Repository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoSpecialty;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.Specialty;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository.ISpecialtyRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class DaoImplSpecialty implements IDaoSpecialty {
    private ISpecialtyRepository repository;

    public DaoImplSpecialty(ISpecialtyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void delete(Specialty entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<Specialty> find(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Specialty> findAll() {
        return repository.findAll();
    }

    @Override
    public Specialty save(Specialty entity) {
        return repository.save(entity);
    }

    @Override
    public Specialty update(Specialty entity) {
        return repository.save(entity);
    }
}
