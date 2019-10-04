package ua.company.spring.SpringUniversityAdmissionSystem.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "exam")
@Data
@NoArgsConstructor
public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date;

    private String address;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
