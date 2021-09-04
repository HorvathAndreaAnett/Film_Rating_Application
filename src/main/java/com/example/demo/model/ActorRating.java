package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ActorRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Actor actor;

    @OneToOne
    private User user;

    @Column
    private Integer rating;

    @Column
    private String review;

}
