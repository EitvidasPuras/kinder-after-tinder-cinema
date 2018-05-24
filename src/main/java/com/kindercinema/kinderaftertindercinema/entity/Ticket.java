package com.kindercinema.kinderaftertindercinema.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date payDate;
    private boolean canceled;
    private boolean reserved;

    @ManyToOne
    private Session session;

    @ManyToOne
    private Seat seat;
}
