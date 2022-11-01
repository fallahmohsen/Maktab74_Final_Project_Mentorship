package com.example.online_quiz2.domain;

import com.example.online_quiz2.base.domian.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Course extends BaseEntity<Long> {
    @ManyToOne
    private User professor;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @ManyToMany
    private Set<User> universityStudent;


}
