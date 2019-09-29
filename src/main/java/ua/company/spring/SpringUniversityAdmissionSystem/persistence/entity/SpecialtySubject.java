package ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "specialty_subject")
public class SpecialtySubject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public SpecialtySubject() {
    }

    public SpecialtySubject(Specialty specialty, Subject subject) {
        this.specialty = specialty;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "SpecialtySubject{" +
                "id=" + id +
                ", specialty=" + specialty +
                ", subject=" + subject +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specialty, subject);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialtySubject that = (SpecialtySubject) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(specialty, that.specialty) &&
                Objects.equals(subject, that.subject);
    }
}
