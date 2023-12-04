package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "title cannot be null")
    @Size(min = 5,message = "title must be more than 4 characters")
    @Column(columnDefinition = "varchar(20) not null CHECK (LENGTH(title) > 4)")
    private String title;
    @NotNull(message = "description cannot be null")
    @Column(columnDefinition = "varchar(200) not null")
    private String description;
    @NotNull(message = "location cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String location;
    @NotNull(message = "salary cannot be null")
    @Positive(message = "enter correct salary")
    @Column(columnDefinition = "int not null CHECK (salary>0)")
    private Integer salary;
    @Column(columnDefinition = "date")
    private LocalDate postingDate;
}
