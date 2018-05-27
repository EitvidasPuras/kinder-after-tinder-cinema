package com.kindercinema.kinderaftertindercinema.repository;

import com.kindercinema.kinderaftertindercinema.entity.KinderUser;
import com.kindercinema.kinderaftertindercinema.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KinderUserRepository extends JpaRepository<KinderUser, Integer> {

    KinderUser findByUser(User user);

    @Query("select k from KinderUser k  where k.age = 18")
    List<KinderUser> getMatchByAge(int age);


}
