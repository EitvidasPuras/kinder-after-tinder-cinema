package com.kindercinema.kinderaftertindercinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "hall")
    @JsonIgnore
    private List<Session> sessions;

    @OneToMany(mappedBy = "hall")
    private List<Row> rows;
}
