package com.kindercinema.kinderaftertindercinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Row {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    @JsonIgnore
    private Hall hall;

    @OneToMany(mappedBy = "row")
    private List<Seat> seats;

}
