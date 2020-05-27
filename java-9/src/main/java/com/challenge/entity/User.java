package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull
    @NotBlank
    @Size(max = 100)
    private String fullName;


    @NotNull
    @NotBlank
    @Email
    @Size(max = 100)
    private String email;


    @NotNull
    @NotBlank
    @Size(max = 50)
    private String nickname;

    @NotBlank
    @NotNull
    @Size(max = 255)
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany
    private List<Candidate> candidates;

    @OneToMany
    private List<Submission> submissions;

    public User() {
    }
}
