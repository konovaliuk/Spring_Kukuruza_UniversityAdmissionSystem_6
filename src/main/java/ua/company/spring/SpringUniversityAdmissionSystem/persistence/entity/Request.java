package ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "request")
public class Request implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "education_option_id")
    private EducationOption educationOption;

    public static class Builder {
        private Integer rating;
        private User user;
        private EducationOption educationOption;

        public Builder setRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setEducationOption(EducationOption educationOption) {
            this.educationOption = educationOption;
            return this;
        }

        public Request build() {
            return new Request(user, rating, educationOption);
        }
    }

    public Request() {
    }

    public Request(User user, Integer rating, EducationOption educationOption) {
        this.user = user;
        this.rating = rating;
        this.educationOption = educationOption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EducationOption getEducationOption() {
        return educationOption;
    }

    public void setEducationOption(EducationOption educationOption) {
        this.educationOption = educationOption;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", rating=" + rating +
                ", user=" + user +
                ", educationOption=" + educationOption +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, user, educationOption);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id) &&
                Objects.equals(rating, request.rating) &&
                Objects.equals(user, request.user) &&
                Objects.equals(educationOption, request.educationOption);
    }
}
