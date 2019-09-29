package ua.company.spring.SpringUniversityAdmissionSystem.persistence.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IDaoGeneric<T, PK extends Serializable> {
    Optional<T> find(PK id);

    List<T> findAll();

    T save(T entity);

    T update(T entity);

    void delete(T entity);
}
