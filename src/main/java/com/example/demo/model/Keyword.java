package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;
}
