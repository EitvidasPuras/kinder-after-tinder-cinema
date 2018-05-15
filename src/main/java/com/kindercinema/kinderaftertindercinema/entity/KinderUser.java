package com.kindercinema.kinderaftertindercinema.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class KinderUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private byte gender;
    private String phoneNumber;
    private String description;
    private byte interestedIn;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
