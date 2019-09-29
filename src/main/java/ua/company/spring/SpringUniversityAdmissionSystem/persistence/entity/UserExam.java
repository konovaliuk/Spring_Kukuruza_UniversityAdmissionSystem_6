package ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_exam")
public class UserExam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public static class Builder {
        private User user;
        private Exam exam;

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setExam(Exam exam) {
            this.exam = exam;
            return this;
        }

        public UserExam build() {
            return new UserExam(user, exam);
        }
    }

    public UserExam() {
    }

    public UserExam(User user, Exam exam) {
        this.user = user;
        this.exam = exam;
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

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @Override
    public String toString() {
        return "UserExam{" +
                "id=" + id +
                ", user=" + user +
                ", exam=" + exam +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, exam);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserExam userExam = (UserExam) o;
        return Objects.equals(id, userExam.id) &&
                Objects.equals(user, userExam.user) &&
                Objects.equals(exam, userExam.exam);
    }
}
