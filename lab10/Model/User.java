package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "name cannot be null")
    @Size(min = 5,message = "enter name more than 4 letters")
    @Pattern(regexp = "^[\\p{Alpha} ]*$",message = "only characters")
    //AND (REGEXP(name, '^[A-Za-z]*$')
    @Column(columnDefinition = "varchar(20) not null CHECK ((LENGTH(name) >= 5) AND (name REGEXP '^[A-Za-z]*$'))")
    private String name;
    @Email(message = "write correct email")
    @Column(columnDefinition = "varchar(20) unique")
    private String email;
    @NotNull(message = "password cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @NotNull(message = "age cannot be null")
//    @Pattern(regexp = "^[0-9]*$",message = "enter correct age")
    @Min(value = 22,message = "enter age more than 21")
    @Column(columnDefinition = "int not null ")
    private Integer age;
    @NotNull(message = "role cannot be null")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$")
    @Column(columnDefinition = "varchar(10) not null check(role ='JOB_SEEKER' or role ='EMPLOYER')")
    private String role;
}
