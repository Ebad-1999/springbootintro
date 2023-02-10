package com.tpe.domain;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity

@Table(name = "t_book")
@NoArgsConstructor
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("bookName")  //this is only for json print name
    private String name;


    @JsonIgnore  //// to ignore infinitive calling, student will be printed book objects
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Student getStudent() {
        return student;
    }


}
