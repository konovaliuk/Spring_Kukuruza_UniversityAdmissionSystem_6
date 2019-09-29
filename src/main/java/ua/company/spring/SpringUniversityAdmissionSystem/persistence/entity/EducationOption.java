package ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "education_option")
public class EducationOption implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @Column(name = "student_limit")
    private Integer studentLimit;

    public EducationOption() {
    }

    public EducationOption(University university, Specialty specialty, Integer studentLimit) {
        this.university = university;
        this.specialty = specialty;
        this.studentLimit = studentLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Integer getStudentLimit() {
        return studentLimit;
    }

    public void setStudentLimit(Integer studentLimit) {
        this.studentLimit = studentLimit;
    }

    @Override
    public String toString() {
        return "EducationOption{" +
                "id=" + id +
                ", university=" + university +
                ", specialty=" + specialty +
                ", studentLimit=" + studentLimit +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, university, specialty, studentLimit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationOption that = (EducationOption) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(university, that.university) &&
                Objects.equals(specialty, that.specialty) &&
                Objects.equals(studentLimit, that.studentLimit);
    }
}
