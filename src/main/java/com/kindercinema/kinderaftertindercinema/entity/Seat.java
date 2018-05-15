package com.kindercinema.kinderaftertindercinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "row_id")
    @JsonIgnore
    private Row row;
}
