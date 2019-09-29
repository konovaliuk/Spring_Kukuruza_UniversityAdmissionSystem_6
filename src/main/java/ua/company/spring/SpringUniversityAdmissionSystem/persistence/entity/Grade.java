package ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "grade")
public class Grade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private Integer result;

    public static class Builder {
        private User user;
        private Subject subject;
        private Integer result;

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setSubject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public Builder setResult(Integer result) {
            this.result = result;
            return this;
        }

        public Grade build() {
            return new Grade(user, subject, result);
        }
    }

    public Grade() {
    }

    public Grade(User user, Subject subject, Integer result) {
        this.user = user;
        this.subject = subject;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", user=" + user +
                ", subject=" + subject +
                ", result=" + result +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, subject, result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(id, grade.id) &&
                Objects.equals(user, grade.user) &&
                Objects.equals(subject, grade.subject) &&
                Objects.equals(result, grade.result);
    }
}
