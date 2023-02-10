package com.tpe.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_student")


public class Student {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotNull(message = "first name can not be null")
    @NotBlank(message = "first name can not be white space")
    @Size(min = 4,max = 25 ,message = "First Name   ${validateValue} must be between {min} and {max}")// A  4 and 25
    @Column(nullable = false,length = 25)

    /*final*/ private String firstName;

    @Column(nullable = false,length = 25)
    /*final*/  private String lastName;


    @Email(message = "please Enter provided email")// aba@gamil.com
    @Column(nullable = false,length = 30,unique = true)
    /*final*/   private String email;
    /*final*/  private String phoneNumber;
    /*final*/  private Integer grade;

    @Setter(AccessLevel.NONE)
    private LocalDateTime createDate=LocalDateTime.now();

    @OneToMany(mappedBy ="student")
    private List<Book> books = new ArrayList<>();




    // cons





}