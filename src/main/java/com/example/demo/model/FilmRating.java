package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class FilmRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Film film;

    @OneToOne
    private User user;

    @Column
    private Integer rating;

    @Column
    private String review;
}
