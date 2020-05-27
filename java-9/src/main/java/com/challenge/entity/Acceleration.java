package com.challenge.entity;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Acceleration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max=100)
    @NotNull
    @NotBlank
    private String name;

    @Size(max=50)
    @NotNull
    @NotBlank
    private String slug;

    @OneToMany
    private List<Candidate> candidates;

    @ManyToOne
    private Challenge challenge;

    @CreatedDate
    private LocalDateTime createdAt;

}
