package com.kindercinema.kinderaftertindercinema.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class KinderUser {

    public final static byte GENDER_MALE = 0;
    public final static byte GENDER_FEMALE = 1;
    public final static byte GENDER_OTHER = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int age;
    private byte gender;
    private String phoneNumber;
    private String description;
    private byte interestedIn;
    @ManyToOne
    private Genre interestedGenre;
    private int interestedAge;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
