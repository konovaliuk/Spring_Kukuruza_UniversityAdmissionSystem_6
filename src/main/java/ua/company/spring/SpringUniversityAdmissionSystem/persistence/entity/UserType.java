package ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_type")
public class UserType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_role")
    private String userRole;

    public UserType() {
    }

    public UserType(String userRole) {
        this.userRole = userRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "id=" + id +
                ", userRole='" + userRole + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userRole);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserType that = (UserType) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userRole, that.userRole);
    }
}
