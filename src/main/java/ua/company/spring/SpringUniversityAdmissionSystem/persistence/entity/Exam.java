package ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "exam")
public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date;

    private String address;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Exam() {
    }

    public Exam(Date date, String address, Subject subject) {
        this.date = date;
        this.address = address;
        this.subject = subject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", subject=" + subject +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, address, subject);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return Objects.equals(id, exam.id) &&
                Objects.equals(date, exam.date) &&
                Objects.equals(address, exam.address) &&
                Objects.equals(subject, exam.subject);
    }
}
