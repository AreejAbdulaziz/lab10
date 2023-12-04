package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "user id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;
    @NotNull(message = "user id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer jobPostId;
}
