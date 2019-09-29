package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.springDataJpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao.IDaoEducationOption;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.EducationOption;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity.University;
import ua.company.spring.SpringUniversityAdmissionSystem.persistence.repository.IEducationOptionRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class DaoImplEducationOption implements IDaoEducationOption {
    private IEducationOptionRepository repository;

    public DaoImplEducationOption(IEducationOptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void delete(EducationOption educationOption) {
        repository.delete(educationOption);
    }

    @Override
    public Optional<EducationOption> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<EducationOption> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<EducationOption> findByUniversity(University university, Pageable pageable) {
        return repository.findByUniversity(university, pageable);
    }

    @Override
    public Optional<EducationOption> findByUniversityIdAndSpecialtyId(Integer universityId, Integer specialtyId) {
        return repository.findByUniversityIdAndSpecialtyId(universityId, specialtyId);
    }

    @Override
    public EducationOption save(EducationOption entity) {
        return repository.save(entity);
    }

    @Override
    public EducationOption update(EducationOption entity) {
        return repository.save(entity);
    }
}
