package com.kindercinema.kinderaftertindercinema.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private boolean is3D;

    @OneToMany(targetEntity = Session.class, mappedBy = "movie")
    private List<Session> sessions;
}
