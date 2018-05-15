package com.kindercinema.kinderaftertindercinema.repository;

import com.kindercinema.kinderaftertindercinema.entity.KinderUser;
import com.kindercinema.kinderaftertindercinema.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KinderUserRepository extends JpaRepository<KinderUser, Integer> {

    KinderUser findByUser(User user);

}
